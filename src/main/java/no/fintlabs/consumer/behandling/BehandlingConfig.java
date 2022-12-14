package no.fintlabs.consumer.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.adapter.models.RequestFintEvent;
import no.fintlabs.core.consumer.shared.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class BehandlingConfig extends ConsumerConfig<BehandlingResource> {

    public BehandlingConfig(ConsumerProps consumerProps) {
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
        return "behandling";
    }
}
