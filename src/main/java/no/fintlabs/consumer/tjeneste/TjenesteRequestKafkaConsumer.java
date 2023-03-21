package no.fintlabs.consumer.tjeneste;

import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Component;

@Component
public class TjenesteRequestKafkaConsumer extends EventRequestKafkaConsumer {
    public TjenesteRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TjenesteConfig consumerConfig) {
        super(eventConsumerFactoryService, consumerConfig);
    }
}
