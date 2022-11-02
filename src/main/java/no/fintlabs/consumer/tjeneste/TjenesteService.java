package no.fintlabs.consumer.tjeneste;

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
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        return cacheManager.<TjenesteResource>create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(TjenesteResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, TjenesteResource> consumerRecord) {
        TjenesteResource tjenesteResource = consumerRecord.value();
        linker.mapLinks(tjenesteResource);
        this.getCache().put(consumerRecord.key(), tjenesteResource, linker.hashCodes(tjenesteResource));

        //log.info("The cache now containes " + this.getCacheSize() + " elements.");
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
