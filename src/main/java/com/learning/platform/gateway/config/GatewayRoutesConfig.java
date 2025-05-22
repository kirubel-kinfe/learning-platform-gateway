package com.learning.platform.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/api/users/**")
                .filters(f -> f
                    .tokenRelay() // ✅ Relay access token from gateway to downstream
                    .rewritePath("/api/users/(?<segment>.*)", "/${segment}")
                )
                .uri("http://localhost:3031")
            )
            .build();
    }
}