package com.leomoreiradev.io.strconsumer.listeners;

import com.leomoreiradev.io.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StrConsumerListener {

    //@SneakyThrows // anotação do lombok que subistitui o throws Exception do metodo
    @StrConsumerCustomListener(groupId = "group-1") //usando listener customizado
    public void create(String message) throws IllegalAccessException {
        log.info("CREATE ::: Receive  message {}", message);
        throw new IllegalAccessException("EXCEPTION...");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: Receive  message {}", message);
    }

    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Receive  message {}", message);
    }

}
