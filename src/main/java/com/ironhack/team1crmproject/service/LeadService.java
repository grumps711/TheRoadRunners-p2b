package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeadService {

    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public List<Lead> showAllLeads(){
        return leadRepository.findAll();
    }

    public void showLeadById(Long number) {
        System.out.println(leadRepository.findLeadByLeadId(number));
    }

    public void save(Lead lead) {
        leadRepository.save(lead);
    }
}
