package com.ironhack.team1crmproject.service;

import com.ironhack.team1crmproject.model.Contact;
import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.model.Opportunity;
import com.ironhack.team1crmproject.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    List<Contact> contactList = new ArrayList<>();
    private final ContactRepository contactRepository;
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    public Contact createContact(Lead lead) {
        Optional<Contact> decisionMaker = contactRepository.findContactByName(lead.getName());
        if (decisionMaker.isEmpty()) {
            contactList.add(contactRepository.save(new Contact(lead.getName(), lead.getRole(), lead.getEmail(), lead.getPhoneNumber())));
            return contactList.get(contactList.size() - 1);
        }
        return decisionMaker.get();
    }
    public void showAllContacts(){

        List<Contact> contacts = contactList;
        contacts.forEach(contact -> System.out.println(contact.toString()));
    }
    public void showContactById(Integer number) {
        try {
            Contact contact = contactList.get(number - 1);
            System.out.println(contact.toString());
        }
        catch (IndexOutOfBoundsException e) {
                System.err.println("Not Found");
        }
    }
}