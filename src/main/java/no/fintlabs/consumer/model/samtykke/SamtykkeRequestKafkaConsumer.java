package no.fintlabs.consumer.model.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeRequestKafkaConsumer extends EventRequestKafkaConsumer<SamtykkeResource> {
    public SamtykkeRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, SamtykkeConfig samtykkeConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, samtykkeConfig, eventTopicService);
    }
}
