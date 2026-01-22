/**
 * 
 */
package com.example.api.demo.service.util;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * @author Tommy Duong, tommy.duong.kc@gmail.com
 * @apiNote RESTful API using Spring Boot and Spring MVC.
 * @category API
 * @implNote
 * 
 * 
 */
@Component
public class ExternalApiHealthIndicator implements HealthIndicator {

	private final RestClient restClient;
	private final MeterRegistry meterRegistry;

    public ExternalApiHealthIndicator(RestClient.Builder builder, MeterRegistry meterRegistry) {
    	// Replace the test base url with your.
        this.restClient = builder.baseUrl("http://localhost:8080").build();
        this.meterRegistry = meterRegistry;
        registerMetrics();
    }

    @Override
    @Retryable(
            value = RestClientException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
        )
    public Health health() {
        try {
            String response = restClient.get().uri("/hello").retrieve().body(String.class);
            return Health.up().withDetail("status", "API is reachable").withDetail("response",response).build();
        } catch (RestClientException e) {
            return Health.down(e).withDetail("status", "API unreachable").build();
        }
        
    }
    
    private void registerMetrics() {
        Gauge.builder("external_api_health", this, h -> h.health().getStatus().equals(Health.up()) ? 1 : 0)
             .description("Health of external API")
             .register(meterRegistry);
    }
    
    
}

