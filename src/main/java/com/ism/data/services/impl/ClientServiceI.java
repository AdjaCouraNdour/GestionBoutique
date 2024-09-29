package com.ism.data.services.impl;

import com.ism.core.Service;
import com.ism.data.entities.Client;

public interface ClientServiceI extends Service<Client> {
    Client getByNumero(String numero) ;
    Client getById(int id) ;
    Client getBySurname(String surname) ;

}
