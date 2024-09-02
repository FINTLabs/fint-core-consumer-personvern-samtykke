package no.fintlabs.consumer.model.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingRequestKafkaConsumer extends EventRequestKafkaConsumer<BehandlingResource> {
    public BehandlingRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, BehandlingConfig behandlingConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, behandlingConfig, eventTopicService);
    }
}
