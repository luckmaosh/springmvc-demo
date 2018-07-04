package com.niux.spring.aop;

import javax.security.auth.login.AccountException;

public class AopServiceImpl implements IAopService {

    private String name = "forezp";

    public void withAop() throws Exception {

        System.out.println("with aop run: " + name);

        if (name.trim().length() == 0)
            throw new AccountException("name cannot be null");
    }

    public void withoutAop() throws Exception {
        System.out.println("without aop");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}