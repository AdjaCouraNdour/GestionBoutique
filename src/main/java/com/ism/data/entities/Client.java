package com.ism.data.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name="client")

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 20,unique = true)
    private String surname;

    @Column(length = 9,unique = true)
    private String telephone;

    @Column(length = 255,unique = true)
    private String address;

    @OneToOne
    @JoinColumn(name = "userId", nullable = true)
    // @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Dette> listeDette = new ArrayList<>();

    public void setListeDette(Dette dette) {
        listeDette.add(dette);
        dette.setClient(this); 
    }

    
    // @OneToMany
    // public void setListeDette(Dette dette) {
    //     listeDette.add(dette);
    // }

}
