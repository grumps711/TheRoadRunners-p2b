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
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "decisionMaker")
    private List<Opportunity> opportunities;

    public Contact(String name, String role, String email, String phoneNumber) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", account=" + account +
                ", opportunities=" + opportunities +
                '}';
    }
}
