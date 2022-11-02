package no.fintlabs.consumer.tjeneste;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class TjenesteEventKafkaProducer extends EventKafkaProducer {
    public TjenesteEventKafkaProducer(EventProducerFactory eventProducerFactory, TjenesteConfig tjenesteConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, tjenesteConfig, eventTopicService);
    }
}
