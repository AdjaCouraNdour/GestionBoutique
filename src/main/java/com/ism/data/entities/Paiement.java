package com.ism.data.entities;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="paiement")

public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    private LocalDate date;
    private double montant;
    
    @ManyToOne
    @JoinColumn(name = "detteId", referencedColumnName = "id")
    private Dette dette;

    public Paiement() {
        date=LocalDate.now();
    }

}
