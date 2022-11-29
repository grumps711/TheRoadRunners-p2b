package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
}
