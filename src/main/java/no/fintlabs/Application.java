package no.fintlabs;

import no.fint.antlr.EnableFintFilter;
import no.fint.relations.annotations.EnableFintRelations;
import no.fintlabs.cache.CacheObjectFactory;
import no.fintlabs.cache.packing.PackingTypes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableFintFilter
@EnableFintRelations
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // TODO: 25/03/2022 Move to config (FC-79)
    @Bean
    public CacheObjectFactory getFintCache() {
        return new CacheObjectFactory<>(PackingTypes.DEFLATE);
    }
}
