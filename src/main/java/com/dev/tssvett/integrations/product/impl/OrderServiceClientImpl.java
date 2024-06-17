package com.dev.tssvett.integrations.product.impl;

import com.dev.tssvett.dto.DeliverDto;
import com.dev.tssvett.dto.StatusDto;
import com.dev.tssvett.enums.OrderStatus;
import com.dev.tssvett.integrations.product.OrderServiceClient;
import com.dev.tssvett.integrations.product.request.OrderDeliveryRequest;
import com.dev.tssvett.integrations.product.request.OrderStatusRequest;
import com.dev.tssvett.properties.OrderProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Service
public class OrderServiceClientImpl implements OrderServiceClient {
    private final WebClient productServiceWebClient;
    private final OrderProperties orderProperties;

    OrderServiceClientImpl(@Qualifier("productServiceWebClient") WebClient productServiceWebClient, OrderProperties orderProperties) {
        this.orderProperties = orderProperties;
        this.productServiceWebClient = productServiceWebClient;
    }

    @Override
    public StatusDto changeOrderStatus(OrderStatusRequest orderStatusRequest) {
        StatusDto statusDto = StatusDto.builder().status(OrderStatus.valueOf(orderStatusRequest.getStatus())).build();
        try {
            return productServiceWebClient
                    .patch()
                    .uri(orderProperties.getMethods().getSetOrderStatus().replace("{orderId}", orderStatusRequest.getOrderId()))
                    .body(BodyInserters.fromValue(statusDto))
                    .header("customerId", orderStatusRequest.getCustomerId().toString())
                    .retrieve()
                    .bodyToMono(StatusDto.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with product service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with product service: " + e.getMessage());

            return null;
        }
    }


    @Override
    public DeliverDto changeDeliveryDate(OrderDeliveryRequest orderDeliveryRequest) {
        DeliverDto deliverDto = DeliverDto.builder().deliveryDate(orderDeliveryRequest.getDeliveryDate()).build();
        try {
            return productServiceWebClient
                    .patch()
                    .uri(orderProperties.getMethods().getSetDeliveryDate().replace("{orderId}", orderDeliveryRequest.getOrderId()))
                    .body(BodyInserters.fromValue(deliverDto))
                    .header("customerId", orderDeliveryRequest.getCustomerId().toString())
                    .retrieve()
                    .bodyToMono(DeliverDto.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with product service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with product service: " + e.getMessage());

            return null;
        }
    }
}
