package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Contact;
import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.model.Opportunity;
import com.ironhack.team1crmproject.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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



    public void showAllContacts(){
        List<Contact> contacts = contactRepository.findAll();
        contacts.forEach(contact -> System.out.println(contact.toString()));
    }

    public void showContactById(Long number) {
        Optional<Contact> contact = contactRepository.findById(number);
        if(contact.isPresent()){
            System.out.println(contact.get().toString());
        }else{
            System.out.println("Not Found");
        }
    }
}
