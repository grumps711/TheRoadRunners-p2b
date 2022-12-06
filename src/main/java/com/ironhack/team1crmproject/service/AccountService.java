package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.repository.AccountRepository;
import org.springframework.stereotype.Service;


public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
}
