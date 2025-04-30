package com.ratna.microservices.limitsservice.controllers;

import com.ratna.microservices.limitsservice.models.Limits;
import com.ratna.microservices.limitsservice.service.LimitsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    private LimitsService limitsService;

    public LimitsController(LimitsService limitsService) {
        this.limitsService = limitsService;
    }

    @GetMapping("/limits")
    public Limits getLimits() {
        return limitsService.getLimits();
    }

}
