package com.dev.tssvett.integrations.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderStatusRequest {
    private String orderId;
    private String status;
    private Long customerId;
}
