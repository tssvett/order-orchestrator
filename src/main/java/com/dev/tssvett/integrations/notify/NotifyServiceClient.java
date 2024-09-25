package com.dev.tssvett.integrations.notify;

import com.dev.tssvett.integrations.notify.request.NotifyRequest;

import java.util.UUID;

public interface NotifyServiceClient {

    String notify(NotifyRequest notifyRequest);
}
