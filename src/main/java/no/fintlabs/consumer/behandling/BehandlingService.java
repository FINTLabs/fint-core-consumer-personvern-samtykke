package no.fintlabs.consumer.behandling;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.samtykke.BehandlingResource;
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
public class BehandlingService extends CacheService<BehandlingResource> {

    private final EntityKafkaConsumer<BehandlingResource> entityKafkaConsumer;

    private final BehandlingLinker linker;

    public BehandlingService(
            BehandlingConfig consumerConfig,
            CacheManager cacheManager,
            EntityKafkaConsumer<BehandlingResource> entityKafkaConsumer,
            BehandlingLinker linker) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<BehandlingResource> initializeCache(CacheManager cacheManager, ConsumerConfig<BehandlingResource> consumerConfig, String s) {
        return cacheManager.<BehandlingResource>create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(BehandlingResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, BehandlingResource> consumerRecord) {
        BehandlingResource fravarResource = consumerRecord.value();
        linker.mapLinks(fravarResource);
        this.getCache().put(consumerRecord.key(), fravarResource, linker.hashCodes(fravarResource));

        //log.info("The cache now containes " + this.getCacheSize() + " elements.");
    }

    @Override
    public Optional<BehandlingResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(BehandlingResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
