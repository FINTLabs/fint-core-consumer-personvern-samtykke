package no.fintlabs.consumer.samtykke;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeEventKafkaProducer extends EventKafkaProducer {
    public SamtykkeEventKafkaProducer(EventProducerFactory eventProducerFactory, SamtykkeConfig samtykkeConfig) {
        super(eventProducerFactory, samtykkeConfig);
    }
}
