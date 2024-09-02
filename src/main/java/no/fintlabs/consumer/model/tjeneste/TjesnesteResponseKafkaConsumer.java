package no.fintlabs.consumer.model.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class TjesnesteResponseKafkaConsumer extends EventResponseKafkaConsumer<TjenesteResource> {

    public TjesnesteResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TjenesteConfig tjenesteConfig, TjenesteLinker tjenesteLinker) {
        super(eventConsumerFactoryService, tjenesteConfig, tjenesteLinker);
    }

}
