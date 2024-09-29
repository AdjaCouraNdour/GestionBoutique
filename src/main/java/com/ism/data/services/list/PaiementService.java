package com.ism.data.services.list;

import java.util.List;

import com.ism.data.entities.Paiement;
import com.ism.data.repository.interfaces.PaiementRepositoryI;
import com.ism.data.services.impl.PaiementServiceI;

public class PaiementService implements PaiementServiceI{

   PaiementRepositoryI repo;

    public PaiementService(PaiementRepositoryI repo) {
        this.repo = repo;
    }

    @Override
    public boolean save(Paiement object) {
        return repo.insert(object);
    }

    @Override
    public List<Paiement> show() {
        return repo.selectAll();
    }

  
}
