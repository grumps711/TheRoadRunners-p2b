package com.ironhack.team1crmproject.service;

import org.springframework.stereotype.Service;


public class OpportunityService {
    private final OpportunityService opportunityService;

    public OpportunityService(OpportunityService opportunityService) {
        this.opportunityService = opportunityService;
    }
}
