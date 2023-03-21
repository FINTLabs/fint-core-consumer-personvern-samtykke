package no.fintlabs.consumer.behandling;

import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Component;

@Component
public class BehandlingRequestKafkaConsumer extends EventRequestKafkaConsumer {
    public BehandlingRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BehandlingConfig consumerConfig) {
        super(eventConsumerFactoryService, consumerConfig);
    }
}
