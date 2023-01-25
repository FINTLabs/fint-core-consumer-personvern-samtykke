package no.fintlabs.consumer.samtykke;

import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeResponseKafkaConsumer extends EventResponseKafkaConsumer {

    public SamtykkeResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, SamtykkeConfig samtykkeConfig) {
        super(eventConsumerFactoryService, samtykkeConfig);
    }

}
