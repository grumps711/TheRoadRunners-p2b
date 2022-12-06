package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.repository.AccountRepository;
import com.ironhack.team1crmproject.repository.ContactRepository;
import org.springframework.stereotype.Service;


public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
}
