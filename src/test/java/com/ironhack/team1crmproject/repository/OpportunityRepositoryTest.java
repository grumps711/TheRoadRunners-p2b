package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.Team1CrmProjectApplication;
import com.ironhack.team1crmproject.model.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpportunityRepositoryTest {
    @MockBean
    Team1CrmProjectApplication team1CrmProjectApplication;
    @Autowired
    OpportunityRepository opportunityRepository;
    @Autowired
    ContactRepository contactRepository;
    @Autowired
    LeadRepository leadRepository;
    @Autowired
    AccountRepository accountRepository;
    List<Lead> totalLeads = new ArrayList<>();
    List<Opportunity> totalOpportunities = new ArrayList<>();
    List<Contact> totalContacts = new ArrayList<>();
    List<Account> totalAccounts = new ArrayList<>();

    /**
     * The way we convert Leads into Opportunities to avoid any Lazy or Persistent is without passing a variable as the argument,
     * instead creating it separated and pass it by getting it from the repository with id saved in a list as a field
     */
    @BeforeEach
    void setUp() {
        totalLeads.add(leadRepository.save(new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack")));
        totalLeads.add(leadRepository.save(new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack")));
        totalLeads.add(leadRepository.save(new Lead("Silvi", "Catalonier", "silvi@gmail.com", "999999999", "CataloniaHotels")));

        totalContacts.add(getContact(totalLeads.get(0)));
        totalContacts.add(getContact(totalLeads.get(1)));
        totalContacts.add(getContact(totalLeads.get(2)));

        totalAccounts.add(getAccount(totalLeads.get(0).getCompanyName()));
        totalAccounts.add(getAccount(totalLeads.get(1).getCompanyName()));
        totalAccounts.add(getAccount(totalLeads.get(2).getCompanyName()));

        totalOpportunities.add(opportunityRepository.save(new Opportunity(TruckType.BOX,6)));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(0).getOpportunityId()).setDecisionMaker(contactRepository.findContactByContactId(totalContacts.get(0).getContactId()));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(0).getOpportunityId()).setAccount(accountRepository.findAccountByAccountId(totalAccounts.get(0).getAccountId()));
        leadRepository.delete(leadRepository.findLeadByLeadId(totalLeads.get(0).getLeadId()));

        totalOpportunities.add(opportunityRepository.save(new Opportunity(TruckType.FLATBED,30)));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(1).getOpportunityId()).setDecisionMaker(contactRepository.findContactByContactId(totalContacts.get(1).getContactId()));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(1).getOpportunityId()).setAccount(accountRepository.findAccountByAccountId(totalAccounts.get(1).getAccountId()));
        leadRepository.delete(leadRepository.findLeadByLeadId(totalLeads.get(1).getLeadId()));

        totalOpportunities.add(opportunityRepository.save(new Opportunity(TruckType.BOX,15)));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(2).getOpportunityId()).setDecisionMaker(contactRepository.findContactByContactId(totalContacts.get(2).getContactId()));
        opportunityRepository.findOpportunityByOpportunityId(totalOpportunities.get(2).getOpportunityId()).setAccount(accountRepository.findAccountByAccountId(totalAccounts.get(2).getAccountId()));
        leadRepository.delete(leadRepository.findLeadByLeadId(totalLeads.get(2).getLeadId()));
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
        // STEP 1
        totalLeads.add(leadRepository.save(new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack")));
        // STEP 2
        totalContacts.add(contactRepository.save(getContact(totalLeads.get(3))));
        // STEP 3
        totalAccounts.add(accountRepository.save(getAccount(totalLeads.get(3).getCompanyName())));
        // STEP 4
        var alfredOpportunity = opportunityRepository.save(createNewOpportunity(totalLeads.get(3), TruckType.BOX, 100));
        opportunityRepository.findOpportunityByOpportunityId(alfredOpportunity.getOpportunityId()).setDecisionMaker(contactRepository.findContactByContactId(totalContacts.get(3).getContactId()));
        opportunityRepository.findOpportunityByOpportunityId(alfredOpportunity.getOpportunityId()).setAccount(accountRepository.findAccountByAccountId(totalAccounts.get(3).getAccountId()));

        assertEquals(0, leadRepository.findAll().size());
        assertEquals(4, contactRepository.findAll().size());
        assertEquals(2, accountRepository.findAll().size());
        assertEquals(4, opportunityRepository.findAll().size());
    }

    private Opportunity createNewOpportunity(Lead lead, TruckType truck, int quantity) {
        leadRepository.delete(lead);
        return new Opportunity(truck, quantity);
    }
    private Account getAccount(String companyName) {
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