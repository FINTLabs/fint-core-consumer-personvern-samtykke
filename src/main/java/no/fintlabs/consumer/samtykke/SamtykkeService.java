package no.fintlabs.consumer.samtykke;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.Optional;

@Slf4j
@Service
public class SamtykkeService extends CacheService<SamtykkeResource> {

    private final EntityKafkaConsumer<SamtykkeResource> entityKafkaConsumer;

    private final SamtykkeLinker linker;

    private final SamtykkeResponseKafkaConsumer responseKafkaConsumer;

    public SamtykkeService(
            SamtykkeConfig consumerConfig,
            CacheManager cacheManager,
            SamtykkeEntityKafkaConsumer entityKafkaConsumer,
            SamtykkeLinker linker,
            SamtykkeResponseKafkaConsumer responseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.responseKafkaConsumer = responseKafkaConsumer;
    }

    @Override
    protected Cache<SamtykkeResource> initializeCache(CacheManager cacheManager, ConsumerConfig<SamtykkeResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(SamtykkeResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, SamtykkeResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        SamtykkeResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            handleEntitiesWithEvent(consumerRecord, resource);
        }
    }

    private void handleEntitiesWithEvent(ConsumerRecord<String, SamtykkeResource> consumerRecord, SamtykkeResource resource) {
        Iterator<Header> iterator = consumerRecord.headers().iterator();
        String corrId = "";

        while (iterator.hasNext()) {
            Header header = iterator.next();
            if (header.key().equals("event-corr-id")) corrId = new String(header.value());
        }

        if (StringUtils.isNotBlank(corrId)) {
            responseKafkaConsumer.getEntityCache().add(corrId, resource);
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
