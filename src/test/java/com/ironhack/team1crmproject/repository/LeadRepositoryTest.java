package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Lead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadRepositoryTest {

    @Autowired
    LeadRepository leadRepository;

    @BeforeEach
    void setUp() {
        var basicLeads = List.of(
                new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack"),
                new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack")
        );
        leadRepository.saveAll(basicLeads);
    }
    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }
    @Test
    void getTotalLeads() {
        assertEquals(2, leadRepository.count());
    }
    @Test
    void getTotalLeadsAfterAddOne() {
        var alfredLead = new Lead("Alfred", "Ironhacker", "alfred@gmail.com", "666666666", "Ironhack");
        alfredLead = leadRepository.save(alfredLead);
        assertEquals("Alfred", (alfredLead.getName()));
        assertEquals(3, leadRepository.count());
    }
    @Test
    void getTotalLeadsAfterDeleteOne() {
        var sizeBeforeDelete = leadRepository.count();
        leadRepository.delete(leadRepository.findLeadByLeadId(1L));
        assertEquals(sizeBeforeDelete - 1, leadRepository.count());
    }
    @Test
    void findByCompanyName() {
        var tmpLead = new Lead("Maria", "Ironhacker", "maria@gmail.com", "666666666", "Ironhack");
        leadRepository.save(tmpLead);
        var ironhackLeaders = leadRepository.findByCompanyName("ironhack");
        for (Lead i : ironhackLeaders)
            System.out.println(i.getName());
        assertEquals(3, ironhackLeaders.size());
    }
}