package no.fintlabs.consumer.model.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingResponseKafkaConsumer extends EventResponseKafkaConsumer<BehandlingResource> {

    public BehandlingResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BehandlingConfig behandlingConfig, BehandlingLinker behandlingLinker) {
        super(eventConsumerFactoryService, behandlingConfig, behandlingLinker);
    }

}
