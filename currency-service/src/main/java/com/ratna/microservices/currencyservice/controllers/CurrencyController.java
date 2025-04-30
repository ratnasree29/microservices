package com.ratna.microservices.currencyservice.controllers;

import com.ratna.microservices.currencyservice.models.CurrencyExchange;
import com.ratna.microservices.currencyservice.repositories.CurrencyExchangeRepository;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CurrencyController {

    private final Environment environment;
    private final CurrencyExchangeRepository currencyExchangeRepository;

    public CurrencyController(Environment environment, CurrencyExchangeRepository currencyExchangeRepository) {
        this.environment = environment;
        this.currencyExchangeRepository = currencyExchangeRepository;
    }

    @GetMapping("/currency-service/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        CurrencyExchange currencyExchange = currencyExchangeRepository.findCurrencyExchangeByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException("not found");
        }
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return new ResponseEntity<>(currencyExchange, HttpStatus.OK);
    }

    @PostMapping("/currency-service")
    public ResponseEntity saveExchangeValue(@RequestBody CurrencyExchange currencyExchange) {
        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        currencyExchangeRepository.save(currencyExchange);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
