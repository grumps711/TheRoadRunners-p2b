package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpportunityRepositoryTest {
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    AccountRepository accountRepository;
    @BeforeEach
    void setUp() {
        // Creation of Leads
        var basicLeads = List.of(
                new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack"),
                new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack"),
                new Lead("Silvi", "Catalonier", "silvi@gmail.com", "999999999", "CataloniaHotels")
        );
        leadRepository.saveAll(basicLeads);
        var eugeniLead = basicLeads.get(0);
        var joseLead = basicLeads.get(1);
        var silviLead = basicLeads.get(2);

        var opportunities = List.of(
                createANewOpportunity(eugeniLead, TruckType.FLATBED, 6),
                createANewOpportunity(joseLead, TruckType.HYBRID, 2),
                createANewOpportunity(silviLead, TruckType.FLATBED, 30)
        );
        opportunityRepository.saveAll(opportunities);
        /*
        // Creation of Contacts
        var contacts = List.of(
            new Contact(eugeniLead.getName(), eugeniLead.getRole(), eugeniLead.getEmail(), eugeniLead.getPhoneNumber()),
            new Contact(joseLead.getName(), joseLead.getRole(), joseLead.getEmail(), joseLead.getPhoneNumber()),
            new Contact(silviLead.getName(), silviLead.getRole(), silviLead.getEmail(), silviLead.getPhoneNumber())
        );
        contactRepository.saveAll(contacts);

        // Creation of Opportunities
        var opportunities = List.of(
                new Opportunity(TruckType.BOX, 6, contacts.get(0)),
                new Opportunity(TruckType.HYBRID, 2, contacts.get(1)),
                new Opportunity(TruckType.FLATBED, 30, contacts.get(2))
        );
        opportunityRepository.saveAll(opportunities);

        // Creation of Accounts
        var accounts = List.of(
                getAccount(eugeniLead.getCompanyName()),
                getAccount(silviLead.getCompanyName())
        );
        accountRepository.saveAll(accounts);
        */
    }
    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
        leadRepository.deleteAll();
        contactRepository.deleteAll();
        accountRepository.deleteAll();
    }
    @Test
    void checkTotalLeadsAfterCreateOpportunity() {
        assertEquals(0, leadRepository.findAll().size());
    }
    @Test
    void checkTotalOpportunities() {
        assertEquals(3, opportunityRepository.findAll().size());
    }
    @Test
    void checkTotalContacts() {
        assertEquals(3, contactRepository.findAll().size());
    }
    @Test
    void checkTotalAccounts() {
        assertEquals(2, accountRepository.findAll().size());
    }
    @Test
    void opportunityCreationTest() {
        // Creation of new lead
        var alfredLead = leadRepository.save(new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack"));
        // convert 134
        var alfredOpportunity = opportunityRepository.save(createANewOpportunity(alfredLead, TruckType.BOX, 6));
        // tests
        assertNull(leadRepository.findLeadByLeadId(alfredLead.getLeadId()));
        assertEquals(alfredOpportunity, opportunityRepository.findOpportunityByOpportunityId(alfredOpportunity.getOpportunityId()));
    }

    private Opportunity createANewOpportunity(Lead lead, TruckType truck, int quantity) {
        // create contact
        Contact decisionMaker = getContact(lead);
        // create opportunity
        Opportunity opportunity = new Opportunity(truck, quantity, decisionMaker);
        // create account
        Account account = getAccount(lead.getCompanyName());
        account.getContactList().add(decisionMaker);
        account.getOpportunityList().add(opportunity);

        // FILL RELATIONS
        // Contact
        //decisionMaker.setAccount(account);
        //decisionMaker.getOpportunities().add(opportunity);
        // Account
        //opportunity.setAccount(account);

        leadRepository.delete(lead);

        return opportunity;
    }
    private Account getAccount(String companyName) {
        /*
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
            // input from user
            account = new Account(2, companyName, "England", "London", IndustryType.MANUFACTURING);
            accountRepository.save(account);
        }
        return account;
        */

        Optional<Account> account = accountRepository.findAccountsByCompanyName(companyName);
        if (account.isEmpty())
            return (accountRepository.save(new Account(2, companyName, "England", "London", IndustryType.MANUFACTURING)));
        return account.get();
    }
    private Contact getContact(Lead lead) {
        Optional<Contact> decisionMaker = contactRepository.findContactByName(lead.getName());
        if (decisionMaker.isEmpty())
            return contactRepository.save(new Contact(lead.getName(), lead.getRole(), lead.getEmail(), lead.getPhoneNumber()));
        return decisionMaker.get();
    }
}