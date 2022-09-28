package no.fintlabs.consumer.tjeneste;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fint.model.resource.personvern.samtykke.TjenesteResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class TjenesteLinker extends FintLinker<TjenesteResource> {

    public TjenesteLinker() {
        super(TjenesteResource.class);
    }

    public void mapLinks(TjenesteResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public TjenesteResources toResources(Collection<TjenesteResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public TjenesteResources toResources(Stream<TjenesteResource> stream, int offset, int size, int totalItems) {
        TjenesteResources resources = new TjenesteResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(TjenesteResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(TjenesteResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(TjenesteResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}