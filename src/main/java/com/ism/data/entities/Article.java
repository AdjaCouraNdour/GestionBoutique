package com.ism.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ism.data.enums.EtatArticle;

import lombok.Data;

@Data
@Entity
@Table(name="article")

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 10,unique = true)
    private String reference;
    @Column(length = 10,unique = true)
    private String libelle;

    private int prix;
    private double qteStock;

    @Enumerated(EnumType.STRING)
    private EtatArticle etatArticle;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> listeDetails = new ArrayList<>();

    public void setListeDetails(Details details) {
        this.qteStock=this.qteStock-details.getQteDette();
        if(this.qteStock==0){
            this.etatArticle=EtatArticle.Indisponible;
        }else{
            this.etatArticle=EtatArticle.Disponible;  
        }
        listeDetails.add(details);

    }
    public void setQteStock(double qteStock) {
        this.qteStock = qteStock;
        if (this.qteStock == 0) {
            this.etatArticle = EtatArticle.Indisponible;
        } else {
            this.etatArticle = EtatArticle.Disponible;
        }    
    }

}
