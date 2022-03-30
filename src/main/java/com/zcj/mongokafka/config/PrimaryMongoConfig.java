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
 * @ClassName: PrimaryMongoConfig
 * @Description: 第一个库
 */
@Configuration
@EnableMongoRepositories(mongoTemplateRef = PrimaryMongoConfig.MONGO_TEMPLATE)
public class PrimaryMongoConfig {
    public static final String MONGO_TEMPLATE = "primaryMongoTemplate";

    @Value("${spring.data.mongodb.primary.uri}")
    private String uri;

    @Primary
    @Bean(name = MONGO_TEMPLATE)
    public MongoTemplate mongoPrimaryTemplate() {
        return new MongoTemplate(mongoPrimaryFactory());
    }

    @Bean
    @Primary
    public MongoDatabaseFactory mongoPrimaryFactory() {
        SimpleMongoClientDatabaseFactory simpleMongoClientDbFactory = new SimpleMongoClientDatabaseFactory(uri);
        return simpleMongoClientDbFactory;
    }
}