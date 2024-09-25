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
public class ComplianceResponse {
    private String status;
    private UUID businessKey;
}