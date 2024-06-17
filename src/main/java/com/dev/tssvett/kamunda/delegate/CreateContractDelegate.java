package com.dev.tssvett.kamunda.delegate;

import com.dev.tssvett.integrations.contract.request.ContractRequest;
import com.dev.tssvett.integrations.contract.ContractServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateContractDelegate implements JavaDelegate {
    private final ContractServiceClient contractServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Register task");
        ContractRequest contractRequest = ContractRequest.builder()
                .account((String) delegateExecution.getVariable("account"))
                .inn((String) delegateExecution.getVariable("inn"))
                .build();
        UUID contractId = contractServiceClient.createContract(contractRequest);
        delegateExecution.setVariable("contractId", contractId.toString());
        log.info("Contract id from register task {}", contractId);
    }
}