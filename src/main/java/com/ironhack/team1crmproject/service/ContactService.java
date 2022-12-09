package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Contact;
import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.repository.AccountRepository;
import com.ironhack.team1crmproject.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public Contact createContact(Lead lead) {
        Optional<Contact> decisionMaker = contactRepository.findContactByName(lead.getName());
        if (decisionMaker.isEmpty())
            return contactRepository.save(new Contact(lead.getName(), lead.getRole(), lead.getEmail(), lead.getPhoneNumber()));
        return decisionMaker.get();
    }
}
