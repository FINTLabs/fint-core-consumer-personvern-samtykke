package no.fintlabs.consumer.behandling;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import org.springframework.stereotype.Service;

@Service
public class BehandlingEventKafkaProducer extends EventKafkaProducer {
    public BehandlingEventKafkaProducer(EventProducerFactory eventProducerFactory, BehandlingConfig behandlingConfig) {
        super(eventProducerFactory, behandlingConfig);
    }
}
