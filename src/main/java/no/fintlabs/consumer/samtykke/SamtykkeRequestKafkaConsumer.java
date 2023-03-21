package no.fintlabs.consumer.samtykke;

import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SamtykkeRequestKafkaConsumer extends EventRequestKafkaConsumer {
    public SamtykkeRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, SamtykkeConfig consumerConfig) {
        super(eventConsumerFactoryService, consumerConfig);
    }
}
