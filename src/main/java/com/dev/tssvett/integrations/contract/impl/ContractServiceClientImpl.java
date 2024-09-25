package com.dev.tssvett.integrations.contract.impl;

import com.dev.tssvett.integrations.contract.request.ContractRequest;
import com.dev.tssvett.integrations.contract.ContractServiceClient;
import com.dev.tssvett.properties.ContractProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
public class ContractServiceClientImpl implements ContractServiceClient {
    private final WebClient contractWebClient;
    private final ContractProperties contractProperties;

    public ContractServiceClientImpl(@Qualifier("contractServiceWebClient") WebClient contractWebClient, ContractProperties contractProperties) {
        this.contractProperties = contractProperties;
        this.contractWebClient = contractWebClient;
    }

    @Override
    public UUID createContract(ContractRequest contractRequest) {
        try {
            return contractWebClient
                    .post()
                    .uri(contractProperties.getMethods().getCreateContract())
                    .body(BodyInserters.fromValue(contractRequest))
                    .retrieve()
                    .bodyToMono(UUID.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with contract service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with contract service: " + e.getMessage());

            return null;
        }
    }

    @Override
    public void deleteContract(UUID id) {
        try {
            contractWebClient
                    .delete()
                    .uri(contractProperties.getMethods().getDeleteContract())
                    .header("contractId", id.toString())
                    .retrieve()
                    .bodyToMono(Void.class)
                    .retryWhen(Retry.fixedDelay(2, Duration.ofMillis(300)))
                    .doOnError(error -> log.error("Error with contract service: " + error.getMessage()))
                    .block();
        } catch (Exception e) {
            log.error("Error with contract service: " + e.getMessage());
        }
    }

}
