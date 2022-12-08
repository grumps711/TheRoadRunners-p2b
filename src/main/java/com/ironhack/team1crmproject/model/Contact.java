package com.ironhack.team1crmproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

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
    //@NotNull
    private String name;

    @Column(name = "role")
    //@NotNull
    private String role;

    @Column(name = "email")
    //@NotNull
    private String email;

    @Column(name = "phone_number")
    //@NotNull
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(mappedBy = "decisionMaker")
    private List<Opportunity> opportunities = new ArrayList<>();

    public Contact(String name, String role, String email, String phoneNumber) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
