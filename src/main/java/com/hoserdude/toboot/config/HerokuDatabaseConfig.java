package com.hoserdude.toboot.config;

import com.mongodb.MongoURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * This is only activated in the heroku environment, otherwise Spring Boot
 * automaticially sets you up for local config
 */
@Profile("heroku")
@Configuration
public class HerokuDatabaseConfig {

    private static Logger logger = LoggerFactory.getLogger(HerokuDatabaseConfig.class);
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoURI mongoURI = new MongoURI(System.getenv("MONGOHQ_URL"));
        return new SimpleMongoDbFactory(mongoURI);
    }

    @Bean
    public DataSource dataSource() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        logger.info("Setting up DataSource for DB: {}", dbUrl);

        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
