package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.payment.PaymentServiceClient;
import com.dev.tssvett.integrations.payment.request.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class PaymentDelegate implements JavaDelegate {
    private final PaymentServiceClient paymentServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Payment task");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .account((String) delegateExecution.getVariable("account"))
                .orderId((UUID) delegateExecution.getVariable("orderId"))
                .totalPrice((BigDecimal) delegateExecution.getVariable("totalPrice"))
                .build();
        String response = paymentServiceClient.payForOrder(paymentRequest);
        delegateExecution.setVariable("paymentRejectFlag", true);
        log.info("Payment completed successfully");
        if (response.equals("FAIL")) {
            throw new BpmnError("Error");
        }
    }
}