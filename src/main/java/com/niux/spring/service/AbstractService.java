package com.niux.spring.service;

public abstract class AbstractService {

    public void init(){
        reload();
    }

    public abstract void reload();
}

