package com.leomoreiradev.paymentserviceconsumer.listener;

import com.leomoreiradev.paymentserviceconsumer.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonListener {

    @KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "jsonContainerFactory")
    public void antiFraud(@Payload Payment payment) throws InterruptedException {
        log.info("Recebendo o pagamento {} ", payment.toString());
        Thread.sleep(2000); // Simulando tempo de resposta
        log.info("Validando Fraude...");
        Thread.sleep(2000);
        log.info("Compra aprovada...");
        Thread.sleep(2000);

    }

    //Aqui temos um listener simulando um consumer de um outro microservice qualquer em um cenario real ex: pdf-service
    @KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "jsonContainerFactory")
    public void pdfGenerator(@Payload Payment payment) throws InterruptedException {
        log.info("Gerando pdf do produto de id {} ...", payment.getId());
        Thread.sleep(2000);
    }

    //Aqui temos um listener simulando um consumer de um outro microservice qualquer em um cenario real ex: email-service
    @KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "jsonContainerFactory")
    public void sendEmail(@Payload Payment payment) throws InterruptedException {
        Thread.sleep(3000);
        log.info("Enviando email de confirmação...");

    }
}
