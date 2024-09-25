package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.deliver.DeliverServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CancelDeliveryDelegate implements JavaDelegate {
    private final DeliverServiceClient deliverServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Cancel delivery task");
        UUID orderId = (UUID) delegateExecution.getVariable("orderId");
        deliverServiceClient.cancelDelivery(orderId);
        log.info("Delivery canceled successfully");
    }
}
