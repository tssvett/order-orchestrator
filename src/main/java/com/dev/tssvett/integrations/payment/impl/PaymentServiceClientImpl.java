package com.dev.tssvett.integrations.payment.impl;

import com.dev.tssvett.integrations.payment.PaymentServiceClient;
import com.dev.tssvett.integrations.payment.request.PaymentRequest;
import com.dev.tssvett.properties.DeliverProperties;
import com.dev.tssvett.properties.PaymentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class PaymentServiceClientImpl implements PaymentServiceClient {
    private final WebClient paymentWebClient;
    private final PaymentProperties paymentProperties;

    public PaymentServiceClientImpl(@Qualifier("paymentServiceWebClient") WebClient paymentWebClient, PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
        this.paymentWebClient = paymentWebClient;
    }

    @Override
    public String payForOrder(PaymentRequest paymentRequest) {
        try {
            return paymentWebClient
                    .post()
                    .uri(paymentProperties.getMethods().getPay())
                    .body(BodyInserters.fromValue(paymentRequest))
                    .retrieve()
                    .bodyToMono(String.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with payment service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with payment service: " + e.getMessage());

            return null;
        }
    }
}
