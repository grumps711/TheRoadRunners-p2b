package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.*;
import com.ironhack.team1crmproject.repository.AccountRepository;
import com.ironhack.team1crmproject.repository.ContactRepository;
import com.ironhack.team1crmproject.repository.LeadRepository;
import com.ironhack.team1crmproject.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OpportunityService {
    private final OpportunityRepository opportunityRepository;
    private final LeadRepository leadRepository;
    private final ContactRepository contactRepository;
    private final AccountRepository accountRepository;
    public Opportunity createOpportunity(Lead lead, TruckType truck, int quantity) {
        leadRepository.delete(lead);
        return opportunityRepository.save(new Opportunity(truck, quantity));
    }
    public void setOpportunityContact(Opportunity opportunity, Contact contact) {
        opportunityRepository.findOpportunityByOpportunityId(opportunity.getOpportunityId()).setDecisionMaker(contactRepository.findContactByContactId(contact.getContactId()));
    }
    public void setOpportunityAccount(Opportunity opportunity, Account account) {
        opportunityRepository.findOpportunityByOpportunityId(opportunity.getOpportunityId()).setAccount(accountRepository.findAccountByAccountId(account.getAccountId()));
    }
    public void save(Opportunity opportunity) {
        opportunityRepository.save(opportunity);
    }

    public void showAllOpportunities(){
        List<Opportunity> opportunities = opportunityRepository.findAll();
        opportunities.forEach(opportunity -> System.out.println(opportunity.toString()));
    }

    public void showOpportunityById(Long number) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(number);
        if(opportunity.isPresent()){
            System.out.println(opportunity.get().toString());
        }else{
            System.out.println("Not Found");
        }
    }
}
