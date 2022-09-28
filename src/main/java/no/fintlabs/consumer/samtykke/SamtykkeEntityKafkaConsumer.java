package no.fintlabs.consumer.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeEntityKafkaConsumer extends EntityKafkaConsumer<SamtykkeResource> {

    public SamtykkeEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            SamtykkeConfig samtykkeConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, samtykkeConfig);
    }
}
