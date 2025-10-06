package com.github.marcoantoniolobo.usercrud.producer;

import com.github.marcoantoniolobo.usercrud.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

class UserProducerTest {

    @Test
    void testSendUser() {
        KafkaTemplate<String, User> kafkaTemplate = Mockito.mock(KafkaTemplate.class);
        UserProducer userProducer = new UserProducer(kafkaTemplate);

        User user = new User();
        user.setId(1L);
        user.setName("Marco");

        userProducer.sendUser(user);

        // Verifica que o método send foi chamado
        Mockito.verify(kafkaTemplate).send("users", user);
    }
}
