package com.ratna.microservices.conversionservice.proxies;

import com.ratna.microservices.conversionservice.models.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-service")
public interface CurrencyExchangeProxy {
    @GetMapping("/currency-service/from/{from}/to/{to}")
    CurrencyConversion retrieveValue(@PathVariable String from, @PathVariable String to);
}
