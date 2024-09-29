package com.ism.core.Repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl<T> implements Repository<T> {

    protected List<T> list = new ArrayList<>();

    @Override
    public boolean insert(T object) {
        return list.add(object);
    }

    @Override
    public List<T> selectAll() {
        return list;
    }
    
}
