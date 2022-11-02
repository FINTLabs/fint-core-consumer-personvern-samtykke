package no.fintlabs.consumer.samtykke;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeEventKafkaProducer extends EventKafkaProducer {
    public SamtykkeEventKafkaProducer(EventProducerFactory eventProducerFactory, SamtykkeConfig samtykkeConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, samtykkeConfig, eventTopicService);
    }
}
