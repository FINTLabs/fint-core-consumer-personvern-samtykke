package no.fintlabs.consumer.config;

import no.fintlabs.core.consumer.shared.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SecurityConfig.class)
public class SecurityConfiguration {
}
