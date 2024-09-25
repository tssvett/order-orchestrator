package com.dev.tssvett.integrations.payment.impl;

import com.dev.tssvett.integrations.payment.PaymentServiceClient;
import com.dev.tssvett.integrations.payment.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Primary
@Service
@Slf4j
@ConditionalOnProperty(name = "rest.payment-service.mock.enabled", havingValue = "true", matchIfMissing = false)
public class PaymentServiceClientMock implements PaymentServiceClient {
    @Override
    public String payForOrder(PaymentRequest paymentRequest) {
        return "FAIL";
    }
}
