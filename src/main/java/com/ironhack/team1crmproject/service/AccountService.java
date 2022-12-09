package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.model.IndustryType;
import com.ironhack.team1crmproject.model.Opportunity;
import com.ironhack.team1crmproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account createAccount(int numberOfEmployees, String companyName, String country, String city, IndustryType industryType) {
        Optional<Account> account = accountRepository.findAccountsByCompanyName(companyName);
        if (account.isEmpty())
            return (accountRepository.save(new Account(numberOfEmployees, companyName, country, city, industryType)));
        return account.get();
    }


    public void showAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> System.out.println(account.toString()));
    }

    public void showAccountById(Long number) {
        Optional<Account> account = accountRepository.findById(number);
        if(account.isPresent()){
            System.out.println(account.get().toString());
        }else{
            System.out.println("Not Found");
        }
    }
}
