package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.notify.NotifyServiceClient;
import com.dev.tssvett.integrations.notify.request.NotifyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RejectNotifyDelegate implements JavaDelegate {
    private final NotifyServiceClient notifyServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Reject notify task");
        NotifyRequest notifyRequest = NotifyRequest.builder()
                .message("Order rejected")
                .customerId((Long) delegateExecution.getVariable("customerId"))
                .orderId((UUID) delegateExecution.getVariable("orderId"))
                .build();
        String response = notifyServiceClient.notify(notifyRequest);
        log.info("Customer notified: {}", response);
    }
}
