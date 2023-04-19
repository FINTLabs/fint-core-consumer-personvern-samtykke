package no.fintlabs.consumer.model.behandling;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingEventKafkaProducer extends EventKafkaProducer {
    public BehandlingEventKafkaProducer(EventProducerFactory eventProducerFactory, BehandlingConfig behandlingConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, behandlingConfig, eventTopicService);
    }
}
