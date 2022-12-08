package com.ironhack.team1crmproject.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ScannerConfig {

    @Bean
    Scanner inputScanner(){
        return new Scanner(System.in);
    }

}
