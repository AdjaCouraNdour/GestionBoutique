package com.ism.data.repository.interfaces;

import com.ism.core.Repository.Repository;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;

public interface UserRepositoryI extends Repository<User> {
    
    User selectById(int id) ;
    User selectBy(UserRole role) ;
    User selectLogin(String login) ;
    User selectByUserEtat(boolean etat) ;

}
