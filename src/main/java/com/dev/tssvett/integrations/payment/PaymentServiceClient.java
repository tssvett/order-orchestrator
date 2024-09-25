package com.dev.tssvett.integrations.payment;

import com.dev.tssvett.integrations.payment.request.PaymentRequest;

import java.math.BigDecimal;
import java.util.UUID;

public interface PaymentServiceClient {
    String payForOrder(PaymentRequest paymentRequest);
}
