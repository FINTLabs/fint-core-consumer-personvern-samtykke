package no.fintlabs.consumer.model.tjeneste;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.samtykke.TjenesteResource;
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
import java.util.Optional;

@Slf4j
@Service
public class TjenesteService extends CacheService<TjenesteResource> {

    private final EntityKafkaConsumer<TjenesteResource> entityKafkaConsumer;
    private final TjenesteLinker linker;

    public TjenesteService(
            TjenesteConfig consumerConfig,
            CacheManager cacheManager,
            TjenesteEntityKafkaConsumer entityKafkaConsumer,
            TjenesteLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<TjenesteResource> initializeCache(CacheManager cacheManager, ConsumerConfig<TjenesteResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        entityKafkaConsumer.registerListener(TjenesteResource.class, this::addResourceToCache);
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

    private void addResourceToCache(ConsumerRecord<String, TjenesteResource> consumerRecord) {
        updateRetensionTime(consumerRecord.headers().lastHeader("topic-retension-time"));
        this.eventLogger.logDataRecieved();
        TjenesteResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
        }
    }

    @Override
    public Optional<TjenesteResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(TjenesteResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
