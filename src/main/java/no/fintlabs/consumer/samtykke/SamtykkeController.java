package no.fintlabs.consumer.samtykke;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
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
@RequestMapping(name = "Samtykke", value = RestEndpoints.SAMTYKKE, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class SamtykkeController extends WriteableConsumerRestController<SamtykkeResource> {

    public SamtykkeController(
            CacheService<SamtykkeResource> cacheService,
            SamtykkeLinker fintLinker,
            SamtykkeConfig samtykkeConfig,
            SamtykkeEventKafkaProducer samtykkeEventKafkaProducer,
            SamtykkeResponseKafkaConsumer samtykkeResponseKafkaConsumer,
            FintFilterService odataFilterService,
            SamtykkeRequestKafkaConsumer samtykkeRequestKafkaConsumer) {
        super(cacheService, fintLinker, samtykkeConfig, samtykkeEventKafkaProducer, samtykkeResponseKafkaConsumer, odataFilterService, samtykkeRequestKafkaConsumer);
    }
}
