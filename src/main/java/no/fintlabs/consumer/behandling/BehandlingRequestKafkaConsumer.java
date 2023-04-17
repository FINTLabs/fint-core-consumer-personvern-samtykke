package no.fintlabs.consumer.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingRequestKafkaConsumer extends EventRequestKafkaConsumer<BehandlingResource> {
    public BehandlingRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BehandlingConfig behandlingConfig) {
        super(eventConsumerFactoryService, behandlingConfig);
    }
}
