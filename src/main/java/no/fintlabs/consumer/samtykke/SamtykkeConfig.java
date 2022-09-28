package no.fintlabs.consumer.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class SamtykkeConfig extends ConsumerConfig<SamtykkeResource> {

    public SamtykkeConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String domainName() {
        return "personvern";
    }

    @Override
    protected String packageName() {
        return "samtykke";
    }

    @Override
    protected String resourceName() {
        return "samtykke";
    }
}
