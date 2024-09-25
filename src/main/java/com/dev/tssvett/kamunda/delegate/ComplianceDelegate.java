package com.dev.tssvett.kamunda.delegate;


import com.dev.tssvett.kafka.compliance.request.ComplianceRequest;
import com.dev.tssvett.kafka.compliance.ComplianceProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class ComplianceDelegate implements JavaDelegate {
    private final ComplianceProducer complianceProducer;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Compliance task");
        String login = (String) delegateExecution.getVariable("login");
        String inn = (String) delegateExecution.getVariable("inn");
        ComplianceRequest complianceRequest = ComplianceRequest.builder()
                .login(login)
                .inn(inn)
                .businessKey(UUID.fromString(delegateExecution.getProcessBusinessKey())).build();
        complianceProducer.sendMessage(complianceRequest);
        log.info("Message sent to kafka");
    }
}
