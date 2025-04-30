package com.ratna.microservices.limitsservice.service;

import com.ratna.microservices.limitsservice.Configuration;
import com.ratna.microservices.limitsservice.models.Limits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LimitsService {

    private final Configuration configuration;

    public LimitsService(Configuration configuration) {
        this.configuration = configuration;
    }


    public Limits getLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
