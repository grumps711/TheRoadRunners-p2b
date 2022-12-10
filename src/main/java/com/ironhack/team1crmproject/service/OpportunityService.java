package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.*;
import com.ironhack.team1crmproject.repository.AccountRepository;
import com.ironhack.team1crmproject.repository.ContactRepository;
import com.ironhack.team1crmproject.repository.LeadRepository;
import com.ironhack.team1crmproject.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

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
        opportunityRepository.findOpportunityByOpportunityId(opportunity.getOpportunityId()).get().setDecisionMaker(contactRepository.findContactByContactId(contact.getContactId()).get());
    }
    public void setOpportunityAccount(Opportunity opportunity, Account account) {
        opportunityRepository.findOpportunityByOpportunityId(opportunity.getOpportunityId()).get().setAccount(accountRepository.findAccountByAccountId(account.getAccountId()));
    }
    public Opportunity findOpportunityByOpportunityId(Long id) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);
        if (opportunity.isEmpty())
            throw new IllegalArgumentException();
        return opportunity.get();

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

    public void setOpportunityStatus(String status, String id) {
        var a = new Opportunity();
        a = opportunityRepository.findOpportunityByOpportunityId(Long.valueOf(id));
        if(status.equalsIgnoreCase("CLOSED-WON")){
            a.setStatus(StatusType.CLOSED_WON);
            System.out.println("Status type changed to closed-won");
        }else{
            a.setStatus(StatusType.CLOSED_LOST);
            System.out.println("Status type changed to closed-lost");
        }
    }

    public void checkOpportunityBy(String s) {
        if(s.equalsIgnoreCase("PRODUCT")){
            opportunityRepository.findAllOpportunitiesByTruck();
        } else if (s.equalsIgnoreCase("COUNTRY")) {
            opportunityRepository.findAllOpportunitiesByCountry();
        } else if (s.equalsIgnoreCase("CITY")){
            opportunityRepository.findAllOpportunitiesByCity();
        } else {
            opportunityRepository.findAllOpportunitiesByIndustry();
        }
    }
}
