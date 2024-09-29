package com.ism.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.ism.data.enums.TypeDette;

import lombok.Data;
import lombok.ToString;
@Data
@Entity
@Table(name="dette")

public class Dette {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private LocalDate date;
    private double montant;
    private double montantVerse;
    private double montantRestant;
    
    @Enumerated(EnumType.STRING)
    private TypeDette typeDette;

    @ColumnDefault(value = "true")
    private boolean archiver;

    // @OneToOne
    // private Client client;

    // @ManyToOne
    // List<Details> listeDetails = new ArrayList<>();

    // @ManyToOne
    // List<Paiement> listePaiements = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "id")
    @ToString.Exclude
    private Client client;

    @OneToMany(mappedBy = "dette", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> listeDetails = new ArrayList<>();

    @OneToMany(mappedBy = "dette", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Paiement> listePaiements = new ArrayList<>();

    public void setListeDetails(Details details) {
        listeDetails.add(details);
    }

    public void setListePaiement(Paiement paiement) {
        this.montantRestant=this.montantRestant-this.montantVerse;
        if(this.montantVerse==this.montant){
            this.montantRestant=0;
            this.typeDette=TypeDette.solde;
        }else{
            this.typeDette=TypeDette.nonSolde;
        }
        listePaiements.add(paiement);
    }

    public Dette() {
        date=LocalDate.now();
    }

}
