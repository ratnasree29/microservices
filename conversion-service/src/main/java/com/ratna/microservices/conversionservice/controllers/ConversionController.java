package com.ratna.microservices.conversionservice.controllers;

import com.ratna.microservices.conversionservice.models.CurrencyConversion;
import com.ratna.microservices.conversionservice.proxies.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class ConversionController {

    private final CurrencyExchangeProxy currencyExchangeProxy;

    public ConversionController(CurrencyExchangeProxy currencyExchangeProxy) {
        this.currencyExchangeProxy = currencyExchangeProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> calculate(@PathVariable String from, @PathVariable String to,
                                              @PathVariable String quantity) {
        HashMap<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        ResponseEntity<CurrencyConversion> response =
        new RestTemplate().getForEntity("http://localhost:2888//currency-service/from/{from}/to/{to}",
                CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = response.getBody();
        currencyConversion.setQuantity(new BigDecimal(quantity));
        BigDecimal multiple = currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(Long.parseLong(quantity)));
        currencyConversion.setTotalCalculateAmount(multiple);
        return new ResponseEntity<>(currencyConversion, HttpStatus.OK);
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> calculatefeign(@PathVariable String from, @PathVariable String to,
                                                        @PathVariable String quantity) {
        CurrencyConversion currencyConversion =
                currencyExchangeProxy.retrieveValue(from, to);
        currencyConversion.setQuantity(new BigDecimal(quantity));
        BigDecimal multiple = currencyConversion.getConversionMultiple().multiply(BigDecimal.valueOf(Long.parseLong(quantity)));
        currencyConversion.setTotalCalculateAmount(multiple);
        return new ResponseEntity<>(currencyConversion, HttpStatus.OK);
    }
}
