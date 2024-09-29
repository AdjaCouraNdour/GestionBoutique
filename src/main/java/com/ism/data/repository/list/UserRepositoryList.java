package com.ism.data.repository.list;

import com.ism.core.Repository.RepositoryImpl;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;
import com.ism.data.repository.interfaces.UserRepositoryI;

public class UserRepositoryList extends RepositoryImpl<User> implements UserRepositoryI {

    @Override
    public User selectById(int id) {
        return
        list.stream()
        .filter(user -> user.getId()==id)
        .findFirst()
        .orElse(null);
    }

  
    public User selectBy(UserRole role) {
        return
        list.stream()
        .filter(user -> user.getUserRole()==role)
        .findFirst()
        .orElse(null);
    }


    @Override
    public User selectLogin(String login) {
        return
        list.stream()
        .filter(user -> user.getLogin().compareTo(login)==0)
        .findFirst()
        .orElse(null);
    }


    @Override
    public User selectByUserEtat(boolean etat) {
        return
        list.stream()
        .filter(user -> user.isUserEtat()==etat)
        .findFirst()
        .orElse(null);    }
  
}
