package no.fintlabs.consumer.model.samtykke;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
public class SamtykkeService extends CacheService<SamtykkeResource> {

    private final EntityKafkaConsumer<SamtykkeResource> entityKafkaConsumer;
    private final SamtykkeLinker linker;
    private final SamtykkeResponseKafkaConsumer samtykkeResponseKafkaConsumer;

    public SamtykkeService(
            SamtykkeConfig consumerConfig,
            CacheManager cacheManager,
            SamtykkeEntityKafkaConsumer entityKafkaConsumer,
            SamtykkeLinker linker, SamtykkeResponseKafkaConsumer samtykkeResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.samtykkeResponseKafkaConsumer = samtykkeResponseKafkaConsumer;
    }

    @Override
    protected Cache<SamtykkeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<SamtykkeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        entityKafkaConsumer.registerListener(SamtykkeResource.class, this::addResourceToCache);
    }

    private void updateRetensionTime(Header header) {
        if (header != null) {
            long retensionTime = getRetensionTime(header.value());
            if (retensionTime != entityKafkaConsumer.getTopicRetensionTime()) {
                entityKafkaConsumer.setTopicRetensionTime(retensionTime);
                getCache().setRetentionPeriodInMs(retensionTime);
            }
        }
    }

    private long getRetensionTime(byte[] value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(value);
        buffer.flip();
        return buffer.getLong();
    }

    private void addResourceToCache(ConsumerRecord<String, SamtykkeResource> consumerRecord) {
        updateRetensionTime(consumerRecord.headers().lastHeader("topic-retension-time"));
        this.eventLogger.logDataRecieved();
        SamtykkeResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null) {
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                samtykkeResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<SamtykkeResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(SamtykkeResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
