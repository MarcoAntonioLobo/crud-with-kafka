package com.github.marcoantoniolobo.usercrud.producer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;

import com.github.marcoantoniolobo.usercrud.model.User;

class UserProducerTest {

	@Test
	void testSendUser() {
		@SuppressWarnings("unchecked")
		KafkaTemplate<String, User> kafkaTemplate = Mockito.mock(KafkaTemplate.class);

		UserProducer producer = new UserProducer(kafkaTemplate);
		User user = new User();
		user.setId(1L);
		user.setName("Marco");

		producer.sendUser(user);

		Mockito.verify(kafkaTemplate).send(Mockito.anyString(), Mockito.eq(user));
	}
}
