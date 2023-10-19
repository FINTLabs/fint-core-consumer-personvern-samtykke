package no.fintlabs.consumer.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class CoreConsumerConfig {

    @Value("${fint.consumer.org-id}")
    private String orgId;

    @Value("${spring.webflux.base-path:}")
    private String contextPath;

    @PostConstruct
    private void logAtStartup() {
        log.info("OrgId: {}", orgId);
    }

    @Qualifier("linkMapper")
    @Bean
    public Map<String, String> linkMapper() {
        return LinkMapper.linkMapper(contextPath);
    }
}
