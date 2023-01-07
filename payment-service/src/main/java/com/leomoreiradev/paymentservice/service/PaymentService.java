package com.leomoreiradev.paymentservice.service;

import com.leomoreiradev.paymentservice.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}
