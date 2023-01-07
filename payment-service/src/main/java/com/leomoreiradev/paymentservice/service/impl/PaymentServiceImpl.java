package com.leomoreiradev.paymentservice.service.impl;

import com.leomoreiradev.paymentservice.model.Payment;
import com.leomoreiradev.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;
    @Override
    public void sendPayment(Payment payment) {
        log.info("Recebi o pagamento {}", payment);
        log.info("Enviando o pagamento...");
        kafkaTemplate.send("payment-topic", payment);
    }
}
