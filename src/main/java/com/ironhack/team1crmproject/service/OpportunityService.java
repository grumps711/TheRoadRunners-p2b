package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.*;
import com.ironhack.team1crmproject.repository.OpportunityRepository;
import org.springframework.stereotype.Service;
@Service
public class OpportunityService {
    private final OpportunityRepository opportunityRepository;

    public OpportunityService(OpportunityRepository opportunityRepository) {
        this.opportunityRepository = opportunityRepository;
    }
    //public Opportunity createOpportunity(Lead lead, TruckType truck, int quantity) {
        //Contact decisionMaker = getContact(lead);
        //Opportunity opportunity = new Opportunity(truck, quantity, decisionMaker);
        //Account account = getAccount(lead.getCompanyName());
        //account.getContactList().add(decisionMaker);
        //account.getOpportunityList().add(opportunity);
        //leadRepository.delete(lead);
    //}
    public void save(Opportunity opportunity) {
        opportunityRepository.save(opportunity);
    }

}
