package com.ironhack.team1crmproject.model;

import com.ironhack.team1crmproject.repository.ContactRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opportunity")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opportunity_id")
    private Long opportunityId;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck")
    private TruckType truck;

    @Column(name = "quantity")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Contact decisionMaker;

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    public Opportunity(TruckType truck, int quantity) {
        this.truck = truck;
        this.quantity = quantity;
        this.status = StatusType.OPEN;
    }
}
