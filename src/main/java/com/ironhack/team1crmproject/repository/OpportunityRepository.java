package com.ironhack.team1crmproject.repository;

import com.ironhack.team1crmproject.model.Opportunity;
import com.ironhack.team1crmproject.model.StatusType;
import com.ironhack.team1crmproject.model.TruckType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
    Optional<Opportunity> findOpportunityByOpportunityId(Long id);
    List<Opportunity> findOpportunityByTruck(TruckType truck);
    List<Opportunity> findOpportunityByQuantity(int quantity);
    List<Opportunity> findOpportunityByStatus(StatusType status);
//    List<Opportunity> findAllByOportunityByTruck();
//    List<Opportunity> findAllByOpportunityAndAccount_City();
//    List<Opportunity> findAllByOpportunityAndAccount_Country();
//    List<Opportunity> findAllByOpportunityAndAccount_IndustryType();

}
