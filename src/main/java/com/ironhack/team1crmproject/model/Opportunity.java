package com.ironhack.team1crmproject.model;

import com.ironhack.team1crmproject.repository.ContactRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Column(name = "truck")
    private TruckType truck;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "status")
    private StatusType status;

    @OneToOne()
    private Contact decisionMaker;

//    opportunityList para la Account
//    @JoinColumn(name = "account_id")

    @ManyToOne(cascade = CascadeType.ALL)
    private Account account;

    public Opportunity(TruckType truck, int quantity, Contact decisionMaker) {
        this.truck = truck;
        this.quantity = quantity;
        this.status = StatusType.OPEN;
        this.decisionMaker = decisionMaker;
    }
}
