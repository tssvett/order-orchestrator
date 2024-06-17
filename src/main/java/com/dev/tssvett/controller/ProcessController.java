package com.dev.tssvett.controller;

import com.dev.tssvett.dto.OrderInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProcessController {
    private final RuntimeService runtimeService;
    private static final String PROCESS_KEY = "OrderConfirmProcess";

    @PostMapping("process/start")
    public UUID startProcess(@RequestBody OrderInfo order) {
        log.info("Starting process {}", PROCESS_KEY);
        String businessKey = runtimeService
                .createProcessInstanceByKey(PROCESS_KEY)
                .setVariable("login", order.getLogin())
                .setVariable("account", order.getAccount())
                .setVariable("inn", order.getInn())
                .setVariable("deliveryAddress", order.getDeliveryAddress())
                .setVariable("totalPrice", order.getTotalPrice())
                .setVariable("customerId", order.getCustomerId())
                .setVariable("orderId", order.getOrderId())
                .businessKey(UUID.randomUUID().toString())
                .execute()
                .getBusinessKey();
        log.info("Process started with business key {}", businessKey);
        return UUID.fromString(businessKey);
    }
}
