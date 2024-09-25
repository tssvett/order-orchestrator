package com.dev.tssvett.kamunda.delegate;

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
public class DeleteContractDelegate implements JavaDelegate {
    private final ContractServiceClient contractServiceClient;

    @Override
    public void execute(DelegateExecution delegateExecution) throws JsonProcessingException {
        log.info("Delete contract task");
        String contractId = (String) delegateExecution.getVariable("contractId");
        contractServiceClient.deleteContract(UUID.fromString(contractId));
        log.info("Contract {} deleted successfully", contractId);
    }
}