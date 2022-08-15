package no.fintlabs.consumer.behandling;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fint.relations.FintLinker;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.WriteableConsumerRestController;
import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Behandling", value = RestEndpoints.BEHANDLING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class BehandlingController extends WriteableConsumerRestController<BehandlingResource> {

    public BehandlingController(
            CacheService<BehandlingResource> cacheService,
            FintLinker<BehandlingResource> fintLinker,
            ConsumerConfig<BehandlingResource> consumerConfig,
            BehandlingEventKafkaProducer behandlingEventKafkaProducer) {
        super(cacheService, fintLinker, consumerConfig, behandlingEventKafkaProducer);
    }
}
