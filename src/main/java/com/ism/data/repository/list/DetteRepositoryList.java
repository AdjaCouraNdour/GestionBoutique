package com.ism.data.repository.list;
import com.ism.core.Repository.RepositoryImpl;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;
import com.ism.data.repository.interfaces.DetteRepositoryI;

public class DetteRepositoryList extends RepositoryImpl<Dette> implements DetteRepositoryI {
    
    @Override
    public Dette selectById(int id) {
        return
        list.stream()
        .filter(dette -> dette.getId()==id)
        .findFirst()
        .orElse(null);
    }

    @Override
    public Dette selectBy(TypeDette type) {
        return
        list.stream()
        .filter(dette -> dette.getTypeDette()==type)
        .findFirst()
        .orElse(null);
    }

   
}
