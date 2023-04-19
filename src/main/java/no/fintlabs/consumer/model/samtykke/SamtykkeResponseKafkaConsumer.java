package no.fintlabs.consumer.model.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class SamtykkeResponseKafkaConsumer extends EventResponseKafkaConsumer<SamtykkeResource> {

    public SamtykkeResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, SamtykkeConfig samtykkeConfig, SamtykkeLinker samtykkeLinker) {
        super(eventConsumerFactoryService, samtykkeConfig, samtykkeLinker);
    }

}
