package com.ism.data.repository.jpa;

import com.ism.core.Repository.RepositoryJPA;
import com.ism.data.entities.Client;
import com.ism.data.repository.interfaces.ClientRepositoryI;
import javax.persistence.TypedQuery;

public class ClientRepositoryJPA extends RepositoryJPA<Client> implements ClientRepositoryI {

    public ClientRepositoryJPA() {
        super(Client.class);
        this.tableName="public.client";
    }

    @Override
    public Client selectById(int id) {
        return em.find(Client.class, id); 
    }


    @Override
    public Client selectByNumero(String numero) {
        TypedQuery<Client> query = em.createQuery(
            String.format("SELECT c FROM %s c WHERE c.telephone = :numero", this.tableName), 
            Client.class);        
        query.setParameter("telephone", numero); 
        return query.getSingleResult(); 
    }

    @Override
    public Client selectBySurname(String surname) {
        TypedQuery<Client> query = em.createQuery(
            String.format("SELECT c FROM %s c WHERE c.surname = :surname",this.tableName), 
            Client.class);
        query.setParameter("surname", surname); 
        return query.getSingleResult(); 
    }
}

