package no.fintlabs.consumer.model.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingEntityKafkaConsumer extends EntityKafkaConsumer<BehandlingResource> {

    public BehandlingEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            BehandlingConfig behandlingConfig,
            EntityTopicService entityTopicService) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, behandlingConfig);
    }
}
