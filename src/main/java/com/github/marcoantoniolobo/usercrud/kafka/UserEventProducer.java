package com.github.marcoantoniolobo.usercrud.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserEventProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;
    private static final String TOPIC = "user-events";

    public UserEventProducer(KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate; // 🔑 atribuição correta
    }

    public void publishEvent(UserEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
