package com.zcj.mongokafka.kafkautils.test;

import com.zcj.mongokafka.config.PrimaryMongoConfig;
import com.zcj.mongokafka.config.SecondaryMongoConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class KafkaConsumerTeset {
    @Resource(name = PrimaryMongoConfig.MONGO_TEMPLATE)
    private MongoTemplate primaryMongoTemplate;     //第一个库的MongoTemplate

    @Resource(name = SecondaryMongoConfig.MONGO_TEMPLATE)
    private MongoTemplate secondaryMongoTemplate;   //第二个库的MongoTemplate
//    类型为MongoTemplate的bean有多个，所以不能用Autowired，必须使用@Resource
//    @Autowired
//    private MongoTemplate mongoTemplate;

    @KafkaListener(topics = "dma.report.netprobe", groupId = "test-consumer-netprobe")
    public void KafkaMsgTraffic(ConsumerRecord<String, String> consumerRecord) {
        String valus = consumerRecord.value();
        primaryMongoTemplate.save(valus, "netprobe");
    }

    @KafkaListener(topics = "dma.report.netdev", groupId = "test-consumer-netdev")
    public void KafkaMsgNetTaskStart(ConsumerRecord<String, String> consumerRecord) {
        String valus = consumerRecord.value();
        secondaryMongoTemplate.save(valus, "netdev");
    }


}
