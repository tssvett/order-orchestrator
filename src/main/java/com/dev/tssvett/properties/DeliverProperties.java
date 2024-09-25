package com.dev.tssvett.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rest.deliver-service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliverProperties {

    private String host;
    private String mock;
    private Methods methods;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Methods {
        private String getDeliverDate;
        private String cancelDelivery;
    }
}
