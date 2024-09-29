package com.ism.data.repository.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ism.core.Repository.RepositoryBDImpl;
import com.ism.data.entities.Details;

public class DetailsRepositoryBD  extends RepositoryBDImpl<Details> {

	@Override
	protected void setInsertParameters(PreparedStatement ps, Details object) throws SQLException {
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
	protected Details converToObject(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'converToObject'");
	}

	@Override
	protected void setIdFromResultSet(Details object, ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setIdFromResultSet'");
	}
    
}
