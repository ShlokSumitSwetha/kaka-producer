package com.kafka.producer.kakaproducer.service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.*;

@Log4j2
@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value) {
        log.info(String.format("Producing Message- Key: %s, Value: %s", key, value));
        CompletableFuture<SendResult<String, String>> sendFuture = kafkaTemplate.sendDefault(key, value);
        sendFuture.thenAccept(metadata -> {
            if (metadata != null) {
                System.out.println("Message sent successfully to partition " + metadata.getRecordMetadata().partition() +
                        " at offset " + metadata.getRecordMetadata().offset());
            } else {
                System.err.println("Failed to send message.");
            }
        });
    }

    @Transactional
    public void sendMessageInTransaction(String key, String message) {
        kafkaTemplate.executeInTransaction((KafkaOperations.OperationsCallback<String, String, Void>) operations -> {
            // Send messages within this callback
            operations.send(key, message);
            return null;
        });
    }
}

