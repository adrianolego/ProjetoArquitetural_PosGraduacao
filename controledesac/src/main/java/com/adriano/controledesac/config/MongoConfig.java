package com.adriano.controledesac.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile({"default"})
@EnableMongoRepositories("com.adriano.controledesac.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${config.mongodb.hostname}")
    private String mongoHost;

    @Value("${config.mongodb.port}")
    private String mongoPort;

    @Value("${config.mongodb.database}")
    private String mongoDB;

    @Override
    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(mongoHost + ":" + mongoPort);
    }

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }
}
