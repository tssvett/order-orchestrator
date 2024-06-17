package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.enums.OrderStatus;
import com.dev.tssvett.integrations.product.OrderServiceClient;
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
public class RejectOrderDelegate implements JavaDelegate {
    private final OrderServiceClient orderServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Reject task");
        UUID orderId = (UUID) delegateExecution.getVariable("orderId");
        log.info("Order id {}", orderId);
        OrderStatus orderStatus = OrderStatus.REJECTED;
        Long customerId = (Long) delegateExecution.getVariable("customerId");
        OrderStatus status = orderServiceClient.changeOrderStatus(orderId.toString(), orderStatus.name(), customerId).getStatus();
        log.info("Status for order {} is {}", orderId, status);
    }
}
