package no.fintlabs.consumer.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class TjenesteRequestKafkaConsumer extends EventRequestKafkaConsumer<TjenesteResource> {
    public TjenesteRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, TjenesteConfig tjenesteConfig) {
        super(eventConsumerFactoryService, tjenesteConfig);
    }
}
