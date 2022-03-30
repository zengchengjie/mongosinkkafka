package com.zcj.mongokafka.kafkautils.test;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Message {
    private LabelsDTO labels;
    private String name;
    private String timestamp;
    private String value;

    @NoArgsConstructor
    @Data
    public static class LabelsDTO {
        private String name;
        private String business;
        private String city;
        private String device;
        private String hostname;
        private String instance;
        private String isp;
        private String job;
        private String port;
        private String sn;
        private String status;
        private String uid;
    }

}
