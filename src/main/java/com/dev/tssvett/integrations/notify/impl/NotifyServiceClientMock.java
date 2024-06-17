package com.dev.tssvett.integrations.notify.impl;

import com.dev.tssvett.integrations.deliver.DeliverServiceClient;
import com.dev.tssvett.integrations.notify.NotifyServiceClient;
import com.dev.tssvett.integrations.notify.request.NotifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Primary
@Service
@Slf4j
@ConditionalOnProperty(name = "rest.notify-service.mock.enabled", havingValue = "true", matchIfMissing = false)
public class NotifyServiceClientMock implements NotifyServiceClient {
    @Override
    public String notify(NotifyRequest notifyRequest) {
        return "SUCCESS";
    }
}
