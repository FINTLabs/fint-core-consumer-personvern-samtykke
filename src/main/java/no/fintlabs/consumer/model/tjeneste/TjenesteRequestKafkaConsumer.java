package no.fintlabs.consumer.model.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class TjenesteRequestKafkaConsumer extends EventRequestKafkaConsumer<TjenesteResource> {
    public TjenesteRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TjenesteConfig tjenesteConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, tjenesteConfig, eventTopicService);
    }
}
