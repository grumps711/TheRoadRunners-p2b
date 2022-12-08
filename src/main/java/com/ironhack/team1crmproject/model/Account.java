package com.ironhack.team1crmproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "number_of_employees")
    private int numberOfEmployees;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "country")
    private String country;

    @Column (name = "balance")
    private Double balance;
    @Column(name = "city")
    private String city;

    @Column(name = "industry_type")
    private IndustryType industryType;

    @OneToMany(mappedBy = "account")
    private List<Contact> contactList;// = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    private List<Opportunity> opportunityList;// = new ArrayList<>();

    public Account(int numberOfEmployees, String companyName, String country, String city, IndustryType industryType) {
        this.numberOfEmployees = numberOfEmployees;
        this.companyName = companyName;
        this.country = country;
        this.city = city;
        this.industryType = industryType;
        contactList = new ArrayList<>();
        opportunityList = new ArrayList<>();
    }
}

