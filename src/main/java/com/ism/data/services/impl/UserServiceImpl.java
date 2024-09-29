package com.ism.data.services.impl;

import com.ism.core.Service;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;

public interface UserServiceImpl extends Service<User> {
     User getById(int id) ;
     User getBy(UserRole role) ;
     User getBylogin(String login) ;
     User getByUserEtat(Boolean etat) ;
     
}
