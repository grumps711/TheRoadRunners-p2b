package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
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
                new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack"),
                new Lead("Silvi", "Catalonier", "silvi@gmail.com", "999999999", "CataloniaHotels")
        );
        leadRepository.saveAll(basicLeads);
        var eugeniLead = basicLeads.get(0);
        var joseLead = basicLeads.get(1);
        var silviLead = basicLeads.get(2);

        // Transform Lead to Opportunity
        var opportunities = List.of(
                createANewOpportunity(eugeniLead, TruckType.BOX, 6),
                createANewOpportunity(joseLead, TruckType.FLATBED, 13),
                createANewOpportunity(silviLead, TruckType.HYBRID, 30)
        );
        opportunityRepository.saveAll(opportunities);

        // Creation of Accounts just after created an opportunity
        var accounts = List.of(
                getAccount(eugeniLead.getCompanyName()),
                getAccount(silviLead.getCompanyName())
        );
        accountRepository.saveAll(accounts);
    }
    @AfterEach
    void tearDown() {
        opportunityRepository.deleteAll();
    }

    @Nested
    @DisplayName("Check SetUp")
    class checkSetUp {
        @Test
        void checkTotalLeadsAfterCreateOpportunity() {
            assertEquals(1, leadRepository.findAll().size());
        }
        @Test
        void checkTotalOpportunities() {
            assertEquals(1, opportunityRepository.findAll().size());
        }
        @Test
        void checkTotalContacts() {
            assertEquals(1, contactRepository.findAll().size());
        }
        @Test
        void checkTotalAccounts() {
            assertEquals(1, accountRepository.findAll().size());
        }
    }

    @Test
    void OpportunityCreationTest() {
        // Creation of new lead
        var alfredLead = new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack");
        alfredLead = leadRepository.save(alfredLead);

        // convert 134
        var alfredOpportunity = createANewOpportunity(alfredLead, TruckType.BOX, 6);
        alfredOpportunity = opportunityRepository.save(alfredOpportunity);
        assertNull(leadRepository.findLeadByLeadId(alfredLead.getLeadId()));
        assertEquals(alfredOpportunity, opportunityRepository.findOpportunityByOpportunityId(alfredOpportunity.getOpportunityId()));

        // after opportunity creation, account must be created or updated
        Account account = getAccount(alfredLead.getCompanyName());
        assertEquals(account, accountRepository.findAccountByAccountId(account.getAccountId()));
        // add opportunity and contact to the Account
        account.getOpportunityList().add(alfredOpportunity);
        account.getContactList().add(getContact(alfredLead));
    }
    private Opportunity createANewOpportunity(Lead lead, TruckType truck, int quantity) {
        Opportunity opportunity;
        Contact decisionMaker = getContact(lead);
        opportunity = new Opportunity(truck, quantity, decisionMaker);
        leadRepository.delete(leadRepository.findLeadByLeadId(lead.getLeadId()));
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
            // input from user
            account = new Account(2, companyName, "England", "London", IndustryType.MANUFACTURING);
            accountRepository.save(account);
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
    @Test
    void
}