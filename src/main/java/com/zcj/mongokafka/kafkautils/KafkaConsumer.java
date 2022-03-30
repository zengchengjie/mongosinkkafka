package com.zcj.mongokafka.kafkautils;

import com.zcj.mongokafka.config.PrimaryMongoConfig;
import com.zcj.mongokafka.config.SecondaryMongoConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class KafkaConsumer {
    @Resource(name = PrimaryMongoConfig.MONGO_TEMPLATE)
    private MongoTemplate primaryMongoTemplate;     //第一个库的MongoTemplate

    @Resource(name = SecondaryMongoConfig.MONGO_TEMPLATE)
    private MongoTemplate secondaryMongoTemplate;   //第二个库的MongoTemplate

    @KafkaListener(topics = "common.vod.edge.traffic", groupId = "consumer-traffic")
    public void KafkaMsgTraffic(ConsumerRecord<String, String> consumerRecord) {
        String valus = consumerRecord.value();
        primaryMongoTemplate.save(valus, "traffic");
    }

    @KafkaListener(topics = "common.vod.sdk.taskstart", groupId = "consumer-taskstart")
    public void KafkaMsgNetTaskStart(ConsumerRecord<String, String> consumerRecord) {
        String valus = consumerRecord.value();
        secondaryMongoTemplate.save(valus, "taskstart");
    }

    @KafkaListener(topics = "common.vod.sdk.taskdata", groupId = "consumer-taskdata")
    public void KafkaMsgTaskData(ConsumerRecord<String, String> consumerRecord) {
        String valus = consumerRecord.value();
        secondaryMongoTemplate.save(valus, "taskdata");
    }

}
