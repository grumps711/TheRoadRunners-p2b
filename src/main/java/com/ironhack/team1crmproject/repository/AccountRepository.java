package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.model.IndustryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
    Optional<Account> findAccountsByCompanyName(String companyName);
    Account findAccountByAccountId(Long id);
    List<Account> findAccountByNumberOfEmployees(int numberOfEmployees);
    List<Account> findAccountByCompanyName(String companyName);
    List<Account> findAccountByCountry(String country);
    List<Account> findAccountByCity(String city);
    List<Account> findAccountByIndustryType(IndustryType industryType);
}
