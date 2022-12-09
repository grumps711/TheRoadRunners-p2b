package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.Team1CrmProjectApplication;
import com.ironhack.team1crmproject.model.Lead;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadRepositoryTest {
    @MockBean
    Team1CrmProjectApplication team1CrmProjectApplication;
    @Autowired
    LeadRepository leadRepository;
    List<Lead> totalLeads = new ArrayList<>();
    @BeforeEach
    void setUp() {
        totalLeads.add(leadRepository.save(new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack")));
        totalLeads.add(leadRepository.save(new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack")));
    }
    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }

    @Test
    void getTotalLeadsAfterDeleteOne() {
        var sizeBeforeDelete = leadRepository.count();
        leadRepository.delete(totalLeads.get(0));
        assertEquals(sizeBeforeDelete - 1, leadRepository.count());
    }
    @Test
    void getTotalLeads() {
        assertEquals(2, leadRepository.count());
    }

    @Test
    void getTotalLeadsAfterAddOne() {
        totalLeads.add(leadRepository.save(new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack")));
        assertEquals("Alfred", (totalLeads.get(2).getName()));
        assertEquals(3, leadRepository.count());
    }

    @Test
    void findByCompanyName() {
        totalLeads.add(leadRepository.save(new Lead("Maria", "Ironhacker", "maria@gmail.com", "666666666", "Ironhack")));
        var ironhackLeaders = leadRepository.findByCompanyName("Ironhack");
        for (Lead i : ironhackLeaders)
            System.out.println(i.getName());
        assertEquals(3, ironhackLeaders.size());
    }

}