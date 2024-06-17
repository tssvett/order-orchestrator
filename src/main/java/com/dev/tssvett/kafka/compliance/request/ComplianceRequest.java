package com.dev.tssvett.kafka.compliance.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ComplianceRequest {
    private String login;
    private String inn;
    private UUID businessKey;
}
