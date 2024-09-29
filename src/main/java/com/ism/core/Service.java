package com.ism.core;

import java.util.List;


public interface Service<T>{
    boolean save(T object);
    List<T> show();
}
