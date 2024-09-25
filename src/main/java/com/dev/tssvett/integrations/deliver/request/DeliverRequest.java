package com.dev.tssvett.integrations.deliver.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliverRequest {
    private String deliveryAddress;
    private UUID orderId;
}
