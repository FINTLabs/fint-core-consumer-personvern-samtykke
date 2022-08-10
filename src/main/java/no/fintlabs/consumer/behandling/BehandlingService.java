package no.fintlabs.consumer.behandling;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.personvern.samtykke.Behandling;
import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Service
public class BehandlingService extends ConsumerService<BehandlingResource> {

    private final BehandlingKafkaConsumer behandlingKafkaConsumer;

    private final BehandlingLinker linker;

    public BehandlingService(BehandlingKafkaConsumer behandlingKafkaConsumer, BehandlingLinker linker, CacheManager cacheManager, ConsumerProps consumerProps) {
        super(cacheManager, Behandling.class, consumerProps, behandlingKafkaConsumer);
        this.behandlingKafkaConsumer = behandlingKafkaConsumer;
        this.linker = linker;
    }

    @Override
    protected Cache<BehandlingResource> initializeCache(CacheManager cacheManager, ConsumerProps consumerProps, String modelName) {
        return cacheManager.<BehandlingResource>create(PackingTypes.POJO, consumerProps.getOrgId(), modelName);
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = behandlingKafkaConsumer.registerListener(BehandlingResource.class, this::addResourceToCache);
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
