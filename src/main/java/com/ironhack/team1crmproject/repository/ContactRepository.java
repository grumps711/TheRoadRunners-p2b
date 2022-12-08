package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

    List<Contact> findContactByContactId(Long contactId);
    List<Contact> findContactByName(String name);
    List<Contact> findContactByRole(String Role);
    List<Contact> findContactByEmail(String email);
    List<Contact> findContactByPhoneNumber(String phoneNumber);

}
