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

        // Transform Lead to Opportunity
        var opportunities = List.of(
                createANewOpportunity(eugeniLead, TruckType.BOX, 6),
                createANewOpportunity(joseLead, TruckType.FLATBED, 13),
                createANewOpportunity(silviLead, TruckType.HYBRID, 30)
        );
        opportunityRepository.saveAll(opportunities);
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

        // TODO
        //  check if contact already exists

    }
    private Opportunity createANewOpportunity(Lead lead, TruckType truck, int quantity) {
        Contact decisionMaker = getContact(lead);

        Opportunity opportunity = new Opportunity(truck, quantity, decisionMaker);

        Account account = getAccount(lead.getCompanyName());
        account.getOpportunityList().add(opportunity);
        account.getContactList().add(getContact(lead));

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
        Optional<Contact> decisionMaker = contactRepository.findContactByName(lead.getName());
        if (decisionMaker.isEmpty()) {
            return contactRepository.save(new Contact(lead.getName(), lead.getRole(), lead.getEmail(), lead.getPhoneNumber()));
        }
        return decisionMaker.get();
    }
}