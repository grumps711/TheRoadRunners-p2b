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

    public List<Lead> showAllLeads(){
        List<Lead> leads = leadRepository.findAll();
        leads.forEach(lead -> System.out.println(lead.toString()));
        return leads;
    }

    public void showLeadById(Long number) {
        Optional<Lead> lead = leadRepository.findById(number);

        if(lead.isPresent()){
            System.out.println(lead.get().toString());
        }else{
            System.out.println("Not Found");
        }
    }

    public void save(Lead lead) {
        leadRepository.save(lead);
    }
}
