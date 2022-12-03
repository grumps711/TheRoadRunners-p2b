package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Lead;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadRepositoryTest {

    @Autowired
    LeadRepository leadRepository;

    @BeforeEach
    void setUp() {
        var EugeniLead = new Lead("Eugeni", "Ironhacker", "eugeni@gmail.com", "666666666", "Ironhack");
        EugeniLead = leadRepository.save(EugeniLead);

        var JoseLead = new Lead("Jose", "Ironhacker", "jose@gmail.com", "666666666", "Ironhack");
        JoseLead = leadRepository.save(JoseLead);
    }

    @AfterEach
    void tearDown() {
        leadRepository.deleteAll();
    }

    @Test
    void getTotalLeads() {
        var totalLeads = leadRepository.getTotal();
        assertEquals(2, totalLeads.size());
    }
}