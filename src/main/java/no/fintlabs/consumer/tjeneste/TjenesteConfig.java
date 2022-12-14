package no.fintlabs.consumer.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class TjenesteConfig extends ConsumerConfig<TjenesteResource> {

    public TjenesteConfig(ConsumerProps consumerProps) {
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
        return "tjeneste";
    }
}
