package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.model.Contact;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        var contact1 = new Contact();
        contactRepository.save(contact1);
    }

    @Test
    void testingAddingOneContact() {
        var countBefore = contactRepository.count();
        var contact2 = new Contact();
        contactRepository.save(contact2);
        var countAfter = contactRepository.count();
        assertEquals(1, countAfter - countBefore);
    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }
}