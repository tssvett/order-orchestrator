package com.dev.tssvett.integrations.deliver.impl;

import com.dev.tssvett.integrations.deliver.DeliverServiceClient;
import com.dev.tssvett.integrations.deliver.request.DeliverRequest;
import com.dev.tssvett.properties.DeliverProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class DeliverServiceClientImpl implements DeliverServiceClient {
    private final WebClient deliverWebClient;
    private final DeliverProperties deliverProperties;

    public DeliverServiceClientImpl(@Qualifier("deliverServiceWebClient") WebClient deliverWebClient, DeliverProperties deliverProperties) {
        this.deliverProperties = deliverProperties;
        this.deliverWebClient = deliverWebClient;
    }

    @Override
    public LocalDateTime getDeliverDate(DeliverRequest deliverRequest) {
        try {
            return deliverWebClient
                    .post()
                    .uri(deliverProperties.getMethods().getGetDeliverDate())
                    .body(BodyInserters.fromValue(deliverRequest))
                    .retrieve()
                    .bodyToMono(LocalDateTime.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with deliver service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with deliver service: " + e.getMessage());

            return null;
        }
    }

    @Override
    public void cancelDelivery(UUID orderId) {
        try {
            deliverWebClient
                    .delete()
                    .uri(deliverProperties.getMethods().getCancelDelivery())
                    .header("orderId", orderId.toString())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with deliver service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with deliver service: " + e.getMessage());
        }
    }
}
