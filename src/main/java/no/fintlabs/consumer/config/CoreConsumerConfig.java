package no.fintlabs.consumer.config;

import no.fint.relations.config.FintRelationsProps;
import no.fint.relations.internal.FintLinkMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CoreConsumerConfig {

    @Value("${spring.webflux.base-path:}")
    private String contextPath;

    @Qualifier("linkMapper")
    @Bean
    public Map<String, String> linkMapper() {
        return LinkMapper.linkMapper(contextPath);
    }
}
