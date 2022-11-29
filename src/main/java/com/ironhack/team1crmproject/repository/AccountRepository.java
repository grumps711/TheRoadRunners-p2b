package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}