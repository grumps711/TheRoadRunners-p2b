package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LeadRepository extends JpaRepository<Lead,Long> {

    List<Lead> findLeadByLeadId(Long leadId);
    List<Lead> findLeadByName(String name);
    List<Lead> findLeadByRole(String role);
    List<Lead> findLeadByEmail(String email);
    List<Lead> findLeadByPhoneNumber(String phoneNumber);
    List<Lead> findLeadByCompanyName(String companyName);

}
