package no.fintlabs.consumer.model.samtykke;

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
            SamtykkeConfig samtykkeConfig,
            EntityTopicService entityTopicService) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, samtykkeConfig);
    }
}
