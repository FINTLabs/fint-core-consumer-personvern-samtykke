package no.fintlabs.consumer.behandling;

import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingResponseKafkaConsumer extends EventResponseKafkaConsumer {

    public BehandlingResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BehandlingConfig behandlingConfig, BehandlingLinker behandlingLinker) {
        super(eventConsumerFactoryService, behandlingConfig, behandlingLinker);
    }

}
