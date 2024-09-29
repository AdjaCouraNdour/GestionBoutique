package com.ism.data.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import com.ism.data.enums.UserRole;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 25,unique = true)
    private String login;
    @Column(length = 25,unique = true)
    private String email;
    @Column(length = 10,unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    
    @ColumnDefault(value = "true")
    @Column(nullable = false)
    private boolean userEtat = true;
    
    @OneToOne
    @JoinColumn(name = "clientId", nullable = true)
    // @ToString.Exclude
    private Client client;
}
