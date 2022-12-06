package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.repository.LeadRepository;
import org.springframework.stereotype.Service;


public class LeadService {
    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

}
