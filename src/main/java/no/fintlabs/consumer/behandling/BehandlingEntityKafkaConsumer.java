package no.fintlabs.consumer.behandling;

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
            EntityTopicService entityTopicService,
            BehandlingConfig behandlingConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, behandlingConfig);
    }
}
