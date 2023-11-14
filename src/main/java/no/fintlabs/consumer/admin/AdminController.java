package no.fintlabs.consumer.admin;

import no.fintlabs.cache.CacheManager;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.admin.ConsumerAdminController;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping(value = RestEndpoints.ADMIN, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController extends ConsumerAdminController {

    @Autowired(required = false)
    private Collection<CacheService<?>> consumerServices;

    public AdminController(ConsumerProps consumerProps, CacheManager cacheManager) {
        super(cacheManager, consumerProps);
    }

    @Override
    protected Collection<CacheService<?>> getConsumerServices() {
        return consumerServices;
    }
}