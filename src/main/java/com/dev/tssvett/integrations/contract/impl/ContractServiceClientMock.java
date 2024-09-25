package com.dev.tssvett.integrations.contract.impl;

import com.dev.tssvett.integrations.contract.request.ContractRequest;
import com.dev.tssvett.integrations.contract.ContractServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Primary
@Service
@Slf4j
@ConditionalOnProperty(name = "rest.contract-service.mock.enabled", havingValue = "true", matchIfMissing = false)
public class ContractServiceClientMock implements ContractServiceClient {
    @Override
    public UUID createContract(ContractRequest contractRequest) {
        return UUID.fromString("c5a1c959-37cc-4ff8-af9a-b8b3adc74616");
    }

    @Override
    public void deleteContract(UUID id) {
        log.info("Contract deleted: {}", id);
    }
}
