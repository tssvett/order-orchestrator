package com.dev.tssvett.integrations.contract;

import com.dev.tssvett.integrations.contract.request.ContractRequest;

import java.util.UUID;

public interface ContractServiceClient {
    UUID createContract(ContractRequest contractRequest);

    void deleteContract(UUID contractId);
}
