package com.ism.data.services.list;

import java.util.List;

import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;
import com.ism.data.repository.interfaces.UserRepositoryI;
import com.ism.data.services.impl.UserServiceImpl;

public class UserService implements UserServiceImpl {
    
    UserRepositoryI repo;

    public UserService(UserRepositoryI repo) {
        this.repo = repo;
    }

    @Override
    public User getById(int id) {
        return
        repo.selectAll().stream()
        .filter(user -> user.getId()==id)
        .findFirst()
        .orElse(null);  
    }

    @Override
    public User getBy(UserRole role) {
        return
        repo.selectAll().stream()
        .filter(user -> user.getUserRole().compareTo(role)==0)
        .findFirst()
        .orElse(null);
    }

    @Override
    public User getBylogin(String login) {
        return
        repo.selectAll().stream()
        .filter(user -> user.getLogin().compareTo(login)==0)
        .findFirst()
        .orElse(null);
    }

   
    @Override
    public boolean save(User object) {
        return repo.insert(object);
    }

    @Override
    public List<User> show() {
        return repo.selectAll();
    }

    @Override
    public User getByUserEtat(Boolean etat) {
        return repo.selectAll().stream()
            .filter(user -> user.isUserEtat() == etat)
            .findFirst()
            .orElse(null);
    }
} 


   
    
