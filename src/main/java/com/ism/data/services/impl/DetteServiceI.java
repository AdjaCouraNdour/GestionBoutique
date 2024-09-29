package com.ism.data.services.impl;

import com.ism.core.Service;
import com.ism.data.entities.Dette;
import com.ism.data.enums.TypeDette;

public interface DetteServiceI extends Service<Dette> {
    Dette getById(int id) ;
    Dette getBy(TypeDette etat);
  
}
