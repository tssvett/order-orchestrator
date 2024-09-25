package com.dev.tssvett.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    private String login;
    private String account;
    private String inn;
    private String deliveryAddress;
    private BigDecimal totalPrice;
    private Long customerId;
    private UUID orderId;
}
