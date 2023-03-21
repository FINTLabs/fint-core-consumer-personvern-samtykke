package no.fintlabs.consumer.tjeneste;

import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TjesnesteResponseKafkaConsumer extends EventResponseKafkaConsumer {

    public TjesnesteResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TjenesteConfig tjenesteConfig) {
        super(eventConsumerFactoryService, tjenesteConfig);
    }

}
