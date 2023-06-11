package com.kafka.producer.kakaproducer;

import com.kafka.producer.kakaproducer.model.User;
import com.kafka.producer.kakaproducer.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KakaProducerApplication implements ApplicationRunner {

	@Autowired
	private KafkaProducerService producerService;

	public static void main(String[] args) {
		SpringApplication.run(KakaProducerApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

		producerService.sendMessage(1, new User("Sumith2", 36) );
	}
}
