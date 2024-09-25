package com.dev.tssvett.integrations.notify.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotifyRequest {
    private String message;
    private UUID orderId;
    private Long customerId;
}
