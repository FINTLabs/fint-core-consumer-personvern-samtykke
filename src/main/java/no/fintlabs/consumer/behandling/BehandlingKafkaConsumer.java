package no.fintlabs.consumer.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.core.consumer.shared.KafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class BehandlingKafkaConsumer extends KafkaConsumer<BehandlingResource> {

    public BehandlingKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService) {
        super("utdanning-vurdering-fravar", entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService);
    }
}
