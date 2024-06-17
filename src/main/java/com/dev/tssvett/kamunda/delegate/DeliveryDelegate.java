package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.deliver.DeliverServiceClient;
import com.dev.tssvett.integrations.deliver.request.DeliverRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeliveryDelegate implements JavaDelegate {
    private final DeliverServiceClient deliverServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Delivery Task");
        DeliverRequest deliverRequest = DeliverRequest.builder()
                .deliveryAddress((String) delegateExecution.getVariable("deliveryAddress"))
                .orderId((UUID) delegateExecution.getVariable("orderId"))
                .build();
        LocalDateTime deliverDate = deliverServiceClient.getDeliverDate(deliverRequest);
        delegateExecution.setVariable("deliverDate", deliverDate);
        log.info("Delivery date for order {} is {}", deliverRequest.getOrderId(), deliverDate);
    }
}
