package no.fintlabs.consumer.tjeneste;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fint.relations.FintLinker;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.WriteableConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Behandling", value = RestEndpoints.TJENESTE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class TjenesteController extends WriteableConsumerRestController<TjenesteResource> {

    public TjenesteController(
            CacheService<TjenesteResource> cacheService,
            FintLinker<TjenesteResource> fintLinker,
            TjenesteConfig consumerConfig,
            TjenesteEventKafkaProducer tjenesteEventKafkaProducer) {
        super(cacheService, fintLinker, consumerConfig, tjenesteEventKafkaProducer);
    }
}
