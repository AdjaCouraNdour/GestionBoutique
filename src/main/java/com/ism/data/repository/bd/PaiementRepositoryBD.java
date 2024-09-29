package com.ism.data.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ism.core.Repository.RepositoryBDImpl;
import com.ism.data.entities.Paiement;
import com.ism.data.repository.interfaces.PaiementRepositoryI;

public class PaiementRepositoryBD extends RepositoryBDImpl<Paiement> implements PaiementRepositoryI{

    @Override
    protected void setInsertParameters(PreparedStatement ps, Paiement object) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInsertParameters'");
    }

    @Override
    protected String generateInsertQuery() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateInsertQuery'");
    }

    @Override
    protected String getTableName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTableName'");
    }

    @Override
    protected Paiement converToObject(ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'converToObject'");
    }

    @Override
    protected void setIdFromResultSet(Paiement object, ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdFromResultSet'");
    }

	@Override
	public Paiement selectById(int id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'selectById'");
	}
    
}
