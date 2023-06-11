package com.kafka.producer.kakaproducer.listener;

import com.kafka.producer.kakaproducer.model.User;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ProducerListenerService implements ProducerListener<Integer, User> {

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        log.info(String.format("Message %s persisted at Offset %d",
                producerRecord.value(), recordMetadata.offset()));
    }

    @Override
    public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata,Exception exception) {
        log.info("Inside Failure:" + producerRecord.value());
    }
}
