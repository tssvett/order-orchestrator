package com.dev.tssvett.integrations.deliver;


import com.dev.tssvett.integrations.deliver.request.DeliverRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DeliverServiceClient {

    LocalDateTime getDeliverDate(DeliverRequest deliverRequest);

    void cancelDelivery(UUID orderId);
}

