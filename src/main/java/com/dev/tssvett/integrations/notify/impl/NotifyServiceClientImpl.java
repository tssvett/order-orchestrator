package com.dev.tssvett.integrations.notify.impl;

import com.dev.tssvett.integrations.notify.NotifyServiceClient;
import com.dev.tssvett.integrations.notify.request.NotifyRequest;
import com.dev.tssvett.properties.NotifyProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class NotifyServiceClientImpl implements NotifyServiceClient {
    private final WebClient notifyWebClient;
    private final NotifyProperties notifyProperties;

    public NotifyServiceClientImpl(@Qualifier("notifyServiceWebClient") WebClient notifyWebClient,NotifyProperties notifyProperties) {
        this.notifyProperties = notifyProperties;
        this.notifyWebClient = notifyWebClient;
    }

    @Override
    public String notify(NotifyRequest notifyRequest) {
        try {
            return notifyWebClient
                    .post()
                    .uri(notifyProperties.getMethods().getRejectNotify())
                    .body(BodyInserters.fromValue(notifyRequest))
                    .retrieve()
                    .bodyToMono(String.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with deliver service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with deliver service: " + e.getMessage());

            return null;
        }
    }
}
