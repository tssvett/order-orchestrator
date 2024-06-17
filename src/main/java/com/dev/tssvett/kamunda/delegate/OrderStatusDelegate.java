package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.product.OrderServiceClient;
import com.dev.tssvett.integrations.product.request.OrderDeliveryRequest;
import com.dev.tssvett.integrations.product.request.OrderStatusRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderStatusDelegate implements JavaDelegate {
    private final OrderServiceClient orderServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Final status task");
        UUID orderId = (UUID) delegateExecution.getVariable("orderId");
        Long customerId = (Long) delegateExecution.getVariable("customerId");
        String status = (boolean) delegateExecution.getVariable("paymentRejectFlag") ? "CANCELLED" : "CONFIRMED";
        log.info("Status for order {} is {}", orderId, status);
        OrderStatusRequest orderStatusRequest = OrderStatusRequest.builder()
                .orderId(orderId.toString())
                .status(status)
                .customerId(customerId)
                .build();
        orderServiceClient.changeOrderStatus(orderStatusRequest);
        if (status.equals("CONFIRMED")) {
            OrderDeliveryRequest orderDeliveryRequest = OrderDeliveryRequest.builder()
                    .orderId(orderId.toString())
                    .customerId(customerId)
                    .deliveryDate((LocalDateTime) delegateExecution.getVariable("deliverDate"))
                    .build();
            orderServiceClient.changeDeliveryDate(orderDeliveryRequest);
            log.info("Delivery date for order {} is {}", orderId, (LocalDateTime) delegateExecution.getVariable("deliverDate"));
        }
        log.info("Order confirmed successfully");
    }
}
