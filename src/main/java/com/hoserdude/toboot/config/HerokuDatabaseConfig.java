package com.hoserdude.toboot.config;

import com.mongodb.MongoURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
