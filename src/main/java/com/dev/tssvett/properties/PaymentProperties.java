package com.dev.tssvett.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rest.payment-service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentProperties {

    private String host;
    private String mock;
    private Methods methods;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Methods {
        private String pay;
    }
}

