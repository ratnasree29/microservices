package com.ratna.microservices.currencyservice.repositories;

import com.ratna.microservices.currencyservice.models.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findCurrencyExchangeByFromAndTo(String from, String to);
}
