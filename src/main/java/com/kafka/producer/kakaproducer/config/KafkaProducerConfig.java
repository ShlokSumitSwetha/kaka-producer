package com.kafka.producer.kakaproducer.config;

import com.kafka.producer.kakaproducer.listener.ProducerListenerService;
import com.kafka.producer.kakaproducer.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Log4j2
@Configuration
public class KafkaProducerConfig {

    @Autowired
    private ProducerListenerService producerListener;

    @Value("${topic.name}")
    private String topic;

    @Bean
    public KafkaTemplate<Integer, User> kafkaTemplate(ProducerFactory<Integer, User> pf) {
        KafkaTemplate<Integer, User> kafkaTemplate = new KafkaTemplate<>(pf);
        kafkaTemplate.setDefaultTopic(topic);
        kafkaTemplate.setProducerListener(producerListener);
        return kafkaTemplate;
    }
}
