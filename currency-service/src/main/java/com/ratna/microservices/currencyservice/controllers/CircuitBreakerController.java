package com.ratna.microservices.currencyservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class CircuitBreakerController {
    private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @CircuitBreaker(name="default", fallbackMethod = "hardcodedresponse")
    @RateLimiter(name="default")
    public String sampleApi() {
        logger.info("Sample api");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:1111/dummy", null);
        return forEntity.getBody();
    }

    public String hardcodedresponse(Exception ex) {
        return "fallback-response";
    }
}
