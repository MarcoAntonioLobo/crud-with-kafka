package com.github.marcoantoniolobo.usercrud.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.github.marcoantoniolobo.usercrud.model.User;

@Component
public class UserProducer {

    private final KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = "user-events";

    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(User user) {
        kafkaTemplate.send(TOPIC, user);
    }
}
