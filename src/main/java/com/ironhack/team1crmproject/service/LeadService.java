package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.repository.LeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeadService {
    private final LeadRepository leadRepository;

    public LeadService(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    public void showAllLeads(){
        List<Lead> leads = leadRepository.findAll();
        leads.forEach(lead -> System.out.println(lead.toString()));
    }

    public void showLeadById(Long number) {
        Optional<Lead> lead = leadRepository.findById(number);

        if(lead.isPresent()){
            System.out.println(lead.get().toString());
        }else{
            System.out.println("Not Found");
        }
    }
    public Lead findLeadById(Long id) {
        //TODO check if the method before is equal to this one
        Optional<Lead> lead = leadRepository.findById(id);
        if (lead.isEmpty())
            throw new IllegalArgumentException();
        return lead.get();
    }

    public void save(Lead lead) {
        leadRepository.save(lead);
    }
}