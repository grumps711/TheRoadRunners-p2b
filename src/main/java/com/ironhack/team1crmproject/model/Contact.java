package com.ironhack.team1crmproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
//
//    contactList para la Account
//    @JoinColumn(name = "account_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToOne(mappedBy = "decisionMaker")
    private Opportunity opportunity;


    public Contact(String name, String role, String email, String phoneNumber) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
