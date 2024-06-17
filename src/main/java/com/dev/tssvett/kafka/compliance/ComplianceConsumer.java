package com.dev.tssvett.kafka.compliance;

import com.dev.tssvett.kafka.compliance.request.ComplianceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ComplianceConsumer {

    private static final String orderTopic = "${topic.name}";
    private final ObjectMapper objectMapper;
    private final RuntimeService runtimeService;

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(String message) throws JsonProcessingException {
        ComplianceResponse complianceResponse = objectMapper.readValue(message, ComplianceResponse.class);
        complianceResponse.setStatus("SUCCESS");
        String processId = complianceResponse.getBusinessKey().toString();
        log.info("Order message consumed successfully {}", message);
        log.info("Compliance result {}", complianceResponse.getStatus());
        log.info("Continuing process {}", processId);
        runtimeService
                .createMessageCorrelation("KafkaMessage")
                .processInstanceBusinessKey(processId)
                .setVariable("complianceStatus", complianceResponse.getStatus())
                .correlate();
    }
}

