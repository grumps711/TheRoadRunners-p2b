package com.ironhack.team1crmproject.utils;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Autowired
    private AccountRepository accountRepository;

//    para guardar cosas en nuestro DB // metodo que se lanza por application
    @Bean
    void loadData(){
        var account1 = new Account();
    }


}
