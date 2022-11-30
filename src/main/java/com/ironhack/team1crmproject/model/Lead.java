package com.ironhack.team1crmproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id")
    private Long leadId;

    @Column(name = "name")
    private String name;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "company_name")
    private String companyName;

    public Lead(String name, String role, String email, String phoneNumber, String companyName) {
        this.name = name;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
    }
}
