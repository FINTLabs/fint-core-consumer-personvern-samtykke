package no.fintlabs.consumer.model.samtykke;

import no.fint.model.resource.personvern.samtykke.SamtykkeResource;
import no.fint.model.resource.personvern.samtykke.SamtykkeResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class SamtykkeLinker extends FintLinker<SamtykkeResource> {

    public SamtykkeLinker() {
        super(SamtykkeResource.class);
    }

    public void mapLinks(SamtykkeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SamtykkeResources toResources(Collection<SamtykkeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public SamtykkeResources toResources(Stream<SamtykkeResource> stream, int offset, int size, int totalItems) {
        SamtykkeResources resources = new SamtykkeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(SamtykkeResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SamtykkeResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(SamtykkeResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}