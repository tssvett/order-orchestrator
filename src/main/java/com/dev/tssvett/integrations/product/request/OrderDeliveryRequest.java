package com.dev.tssvett.integrations.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDeliveryRequest {
    private String orderId;
    private LocalDateTime deliveryDate;
    private Long customerId;
}
