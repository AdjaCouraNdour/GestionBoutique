package com.ism.data.services.list;

import java.util.List;

import com.ism.data.entities.Details;
import com.ism.data.repository.interfaces.DetailsRepositoryI;
import com.ism.data.services.impl.DetailsServiceI;

public class DetailsService implements DetailsServiceI {

    DetailsRepositoryI repo;

	public DetailsService(DetailsRepositoryI repo) {
		this.repo = repo;
	}

	@Override
	public boolean save(Details object) {
        return repo.insert(object);
	}

	@Override
	public List<Details> show() {
        return repo.selectAll();

	}

	
    
}
