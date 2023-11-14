package no.fintlabs.consumer.model.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class SamtykkeConfig extends ConsumerConfig<SamtykkeResource> {

    public SamtykkeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "samtykke";
    }
}
