package com.dev.tssvett.dto;

import com.dev.tssvett.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {

    @NotNull(message = "Status can not be null")
    private OrderStatus status;
}
