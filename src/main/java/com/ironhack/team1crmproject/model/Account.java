package com.ironhack.team1crmproject.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    private int numberOfEmployees;

    private String companyName;

    private String country;

    private String city;

    private IndustryType industryType;

//    TODO

    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    private List<Contact> contactList;

//
    @OneToMany(mappedBy = "account")
    @ToString.Exclude
    private List<Opportunity> opportunityList;


}

