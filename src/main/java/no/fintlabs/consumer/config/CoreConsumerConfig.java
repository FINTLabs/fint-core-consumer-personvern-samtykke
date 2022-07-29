package no.fintlabs.consumer.config;

import no.fint.relations.config.FintRelationsProps;
import no.fint.relations.internal.FintLinkMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class CoreConsumerConfig {

    @Bean
    public FintLinkMapper fintLinkMapper() {
        return new FintLinkMapper();
    }

    @Bean
    public FintRelationsProps getFintRelationsProps() {
        return new FintRelationsProps();
    }
}
