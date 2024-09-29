package com.ism.data.repository.list;

import com.ism.core.Repository.RepositoryImpl;
import com.ism.data.entities.Paiement;
import com.ism.data.repository.interfaces.PaiementRepositoryI;

public class PaiementRepositoryList extends RepositoryImpl<Paiement>  implements PaiementRepositoryI{

	@Override
	public Paiement selectById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'selectById'");
	}


}
