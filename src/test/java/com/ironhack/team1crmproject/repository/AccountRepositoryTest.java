package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        var account1 = new Account();
       accountRepository.save(account1);
    }

    @Test
    void testingAddingOneAccount() {
        var countBefore = accountRepository.count();
        var account2 = new Account();
        accountRepository.save(account2);
        var countAfter = accountRepository.count();
        assertEquals(1, countAfter - countBefore);
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }
}