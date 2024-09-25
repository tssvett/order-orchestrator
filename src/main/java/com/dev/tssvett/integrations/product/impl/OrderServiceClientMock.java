package com.dev.tssvett.integrations.product.impl;

import com.dev.tssvett.dto.DeliverDto;
import com.dev.tssvett.dto.StatusDto;
import com.dev.tssvett.enums.OrderStatus;
import com.dev.tssvett.integrations.product.OrderServiceClient;
import com.dev.tssvett.integrations.product.request.OrderDeliveryRequest;
import com.dev.tssvett.integrations.product.request.OrderStatusRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Primary
@Service
@Slf4j
@ConditionalOnProperty(name = "rest.product-service.mock.enabled", havingValue = "true", matchIfMissing = false)
public class OrderServiceClientMock implements OrderServiceClient {
    @Override
    public StatusDto changeOrderStatus(OrderStatusRequest orderStatusRequest) {
        log.info("Order status changed: {}", orderStatusRequest.getStatus());
        return StatusDto.builder().status(OrderStatus.CONFIRMED).build();
    }

    @Override
    public DeliverDto changeDeliveryDate(OrderDeliveryRequest orderDeliveryRequest) {
        log.info("Delivery date added: {}", orderDeliveryRequest.getDeliveryDate());
        return DeliverDto.builder().deliveryDate(orderDeliveryRequest.getDeliveryDate()).build();
    }
}