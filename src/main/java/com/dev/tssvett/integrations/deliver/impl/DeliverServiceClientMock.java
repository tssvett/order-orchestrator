package com.dev.tssvett.integrations.deliver.impl;

import com.dev.tssvett.integrations.deliver.DeliverServiceClient;
import com.dev.tssvett.integrations.deliver.request.DeliverRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Primary
@Service
@Slf4j
@ConditionalOnProperty(name = "rest.deliver-service.mock.enabled", havingValue = "true", matchIfMissing = false)
public class DeliverServiceClientMock implements DeliverServiceClient {
    @Override
    public LocalDateTime getDeliverDate(DeliverRequest deliverRequest) {
        return LocalDateTime.now().plusDays(2);
    }

    @Override
    public void cancelDelivery(UUID orderId) {
        log.info("Delivery canceled: {}", orderId);
    }
}