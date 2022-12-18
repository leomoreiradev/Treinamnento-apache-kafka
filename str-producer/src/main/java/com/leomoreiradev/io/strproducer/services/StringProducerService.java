package com.leomoreiradev.io.strproducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("str-topic", message).addCallback(
                success -> {
                    if (success != null) {
                        log.info("Send message with success {}", message);
                        log.info("Partition {}, Offset {}",
                                 success.getRecordMetadata().partition(),
                                 success.getRecordMetadata().offset());
                    }
                },
                error -> log.error("Error send message")
        );
    }

}
