package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.model.Contact;
import com.ironhack.team1crmproject.model.IndustryType;
import com.ironhack.team1crmproject.model.Opportunity;
import com.ironhack.team1crmproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    List<Account> accountList = new ArrayList<>();
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account createAccount(int numberOfEmployees, String companyName, String country, String city, IndustryType industryType) {
        Optional<Account> account = accountRepository.findAccountsByCompanyName(companyName);
        if (account.isEmpty()) {
            accountList.add(accountRepository.save(new Account(numberOfEmployees, companyName, country, city, industryType)));
            return accountList.get(accountList.size() - 1);
        }
        return account.get();
    }
    public void showAllAccounts(){
        List<Account> accounts = accountList;
        accounts.forEach(contact -> System.out.println(accounts.toString()));
    }

    public void showAccountById(Integer number) {
        try {
            Account account = accountList.get(number - 1);
            System.out.println(account.toString());
        }
        catch (IndexOutOfBoundsException e) {
            System.err.println("Not Found");
        }
    }
}
