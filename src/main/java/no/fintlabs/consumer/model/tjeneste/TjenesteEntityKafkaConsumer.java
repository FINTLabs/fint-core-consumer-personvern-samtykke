package no.fintlabs.consumer.model.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class TjenesteEntityKafkaConsumer extends EntityKafkaConsumer<TjenesteResource> {

    public TjenesteEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            TjenesteConfig tjenesteConfig,
            EntityTopicService entityTopicService) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, tjenesteConfig);
    }
}
