package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LeadRepository extends JpaRepository<Lead,Long> {

}
