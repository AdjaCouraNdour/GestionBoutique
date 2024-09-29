package com.ism.data.services.list;

import java.util.List;

import com.ism.data.entities.Client;
import com.ism.data.repository.interfaces.ClientRepositoryI;
import com.ism.data.services.impl.ClientServiceI;

public class ClientService implements ClientServiceI{

    ClientRepositoryI repo;
    
    public ClientService(ClientRepositoryI repo) {
        this.repo = repo;
    }

    @Override
    public Client getById(int id) {
        return
        repo.selectAll().stream()
        .filter(client -> client.getId()==id)
        .findFirst()
        .orElse(null);  
    }

    @Override
    public Client getByNumero(String numero) {
        return
        repo.selectAll().stream()
        .filter(client -> client.getTelephone().compareTo(numero)==0)
        .findFirst()
        .orElse(null); 
    }

    @Override
    public Client getBySurname(String surname) {
        return
        repo.selectAll().stream()
        .filter(client -> client.getSurname().compareTo(surname)==0)
        .findFirst()
        .orElse(null); 
    }

    @Override
    public boolean save(Client object) {
        return repo.insert(object);
    }

    @Override
    public List<Client> show() {
        return repo.selectAll();
    }

}
  