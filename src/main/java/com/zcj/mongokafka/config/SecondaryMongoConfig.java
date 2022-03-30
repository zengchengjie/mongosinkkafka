package com.zcj.mongokafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @ClassName: SecondaryMongoConfig
 * @Description: 第二个库
 */
@Configuration
@EnableMongoRepositories(mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {
    public static final String MONGO_TEMPLATE = "secondaryMongoTemplate";


    @Value("${spring.data.mongodb.secondary.uri}")
    private String uri;

    @Primary
    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate mongoSecondaryTemplate() throws Exception {
        return new MongoTemplate(mongoSecondaryFactory());
    }

    @Bean
    @Primary
    public MongoDatabaseFactory mongoSecondaryFactory() throws Exception {
        SimpleMongoClientDatabaseFactory simpleMongoClientDbFactory = new SimpleMongoClientDatabaseFactory(uri);
        return simpleMongoClientDbFactory;
    }
}