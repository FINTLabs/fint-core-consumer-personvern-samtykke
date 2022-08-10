package no.fintlabs.consumer.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fint.relations.FintLinker;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.ConsumerRestController;
import no.fintlabs.core.consumer.shared.resource.ConsumerService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(name = "Behandling", value = RestEndpoints.BEHANDLING, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class BehandlingController  extends ConsumerRestController<BehandlingResource> {
    public BehandlingController(ConsumerService<BehandlingResource> consumerService, FintLinker<BehandlingResource> fintLinker) {
        super(consumerService, fintLinker);
    }

//    // Writable class
//    @GetMapping("/status/{id}")
//    public ResponseEntity getStatus(
//            @PathVariable String id,
//            @RequestHeader(HeaderConstants.ORG_ID) String orgId,
//            @RequestHeader(HeaderConstants.CLIENT) String client) {
//        log.debug("/status/{} for {} from {}", id, orgId, client);
//        return statusCache.handleStatusRequest(id, orgId, linker, BehandlingResource.class);
//    }
//
//    @PostMapping
//    public ResponseEntity postBehandling(
//            @RequestHeader(name = HeaderConstants.ORG_ID) String orgId,
//            @RequestHeader(name = HeaderConstants.CLIENT) String client,
//            @RequestBody BehandlingResource body,
//            @RequestParam(name = "validate", required = false) boolean validate
//    ) {
//        log.debug("postBehandling, Validate: {}, OrgId: {}, Client: {}", validate, orgId, client);
//        log.trace("Body: {}", body);
//        linker.mapLinks(body);
//        Event event = new Event(orgId, Constants.COMPONENT, SamtykkeActions.UPDATE_BEHANDLING, client);
//        event.addObject(objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).convertValue(body, Map.class));
//        event.setOperation(validate ? Operation.VALIDATE : Operation.CREATE);
//        consumerEventUtil.send(event);
//
//        statusCache.put(event.getCorrId(), event);
//
//        URI location = UriComponentsBuilder.fromUriString(linker.self()).path("status/{id}").buildAndExpand(event.getCorrId()).toUri();
//        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
//    }
//
//
//    @PutMapping("/systemid/{id:.+}")
//    public ResponseEntity putBehandlingBySystemId(
//            @PathVariable String id,
//            @RequestHeader(name = HeaderConstants.ORG_ID) String orgId,
//            @RequestHeader(name = HeaderConstants.CLIENT) String client,
//            @RequestBody BehandlingResource body
//    ) {
//        log.debug("putBehandlingBySystemId {}, OrgId: {}, Client: {}", id, orgId, client);
//        log.trace("Body: {}", body);
//        linker.mapLinks(body);
//        Event event = new Event(orgId, Constants.COMPONENT, SamtykkeActions.UPDATE_BEHANDLING, client);
//        event.setQuery("systemid/" + id);
//        event.addObject(objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).convertValue(body, Map.class));
//        event.setOperation(Operation.UPDATE);
//        fintAuditService.audit(event);
//
//        consumerEventUtil.send(event);
//
//        statusCache.put(event.getCorrId(), event);
//
//        URI location = UriComponentsBuilder.fromUriString(linker.self()).path("status/{id}").buildAndExpand(event.getCorrId()).toUri();
//        return ResponseEntity.status(HttpStatus.ACCEPTED).location(location).build();
//    }
}
