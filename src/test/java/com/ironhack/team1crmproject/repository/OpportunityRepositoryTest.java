package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityRepositoryTest {

    OpportunityRepository opportunityRepository;
    ContactRepository contactRepository;
    LeadRepository leadRepository;

    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        // Creation of Leads
        var basicLeads = List.of(
                new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack"),
                new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack")
        );
        leadRepository.saveAll(basicLeads);
        var eugeniLead = basicLeads.get(0);
        var joseLead = basicLeads.get(1);

        // Creation of Contacts
        var contacts = List.of(
                new Contact(eugeniLead.getName(), eugeniLead.getRole(), eugeniLead.getEmail(), eugeniLead.getPhoneNumber()),
                new Contact(joseLead.getName(), joseLead.getRole(), joseLead.getEmail(), joseLead.getPhoneNumber())
        );
        contactRepository.saveAll(contacts);

        // Creation of Contacts
        var accounts = List.of(
                new Account(2, eugeniLead.getCompanyName(), "England", "London", IndustryType.MANUFACTURING, contacts, ),
                new Account(4, joseLead.getCompanyName(), "Switzerland", "Zurich", IndustryType.ECOMMERCE, )
        );
        accountRepository.saveAll(accounts);
        //Creation of Opportunities
        var opportunities = List.of(
                new Opportunity(TruckType.BOX, 6, contacts.get(0)),
                new Opportunity(TruckType.HYBRID, 2, contacts.get(1))
        );
        opportunityRepository.saveAll(opportunities);
    }
    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
    }

    @Test
    void OpportunityCreationTest() {
        var alfredLead = new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack");
        alfredLead = leadRepository.save(alfredLead);

        // convert 134
        var alfredOpportunity = createANewOpportunity(alfredLead, TruckType.BOX, 6);

        alfredOpportunity = opportunityRepository.save(alfredOpportunity);
    }
    private Opportunity createANewOpportunity(Lead lead, TruckType truck, int quantity) {
        Opportunity opportunity;
        Contact decisionMaker = getContact(lead);
        Account account = getAccount(lead.getCompanyName());
        opportunity = new Opportunity(truck, quantity, decisionMaker, account);
        return opportunity;
    }

    private Account getAccount(String companyName) {
        var accounts = accountRepository.findAll();
        int i = 0;
        boolean found = false;
        Account account = null;
        while (i < accounts.size() && !found) {
            if (companyName.equals(accounts.get(i).getCompanyName())) {
                account = accounts.get(i);
                found = true;
            }
            i++;
        }
        if (!found) {
            account = new Account(2, companyName, "England", "London", IndustryType.MANUFACTURING, , ),
        }

        return account;
    }

    private Contact getContact(Lead lead) {
        var contacts = contactRepository.findAll();
        int contact = 0;
        boolean found = false;
        Contact decisionMaker = null;
        while (contact < contacts.size() && !found) {
            if (lead.getName().equals(contacts.get(contact).getName())) {
                decisionMaker = contacts.get(contact);
                found = true;
            }
            contact++;
        }
        if (!found) {
            decisionMaker = new Contact(lead.getName(), lead.getRole(), lead.getEmail(), lead.getPhoneNumber());
            contactRepository.save(decisionMaker);
        }
        return decisionMaker;
    }
}