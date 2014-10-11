package com.hoserdude.toboot.config;

import com.mongodb.MongoURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * This is only activated in the heroku environment, otherwise Spring Boot
 * automaticially sets you up for local config
 */
@Profile("heroku")
@Configuration
public class HerokuDatabaseConfig {
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        return new SimpleMongoDbFactory(mongoURI);
    }

    @Bean
    public URI dbUrl() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));
        return dbUri;
    }
}
