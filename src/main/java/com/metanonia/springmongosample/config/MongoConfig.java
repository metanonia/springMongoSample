package com.metanonia.springmongosample.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Slf4j
@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String url;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(url);
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), database);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        log.info("MongoTemplate create");
        return new MongoTemplate(mongoDatabaseFactory());
    }
}
