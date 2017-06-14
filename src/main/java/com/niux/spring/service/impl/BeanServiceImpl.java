package com.niux.spring.service.impl;

import com.niux.spring.service.BeanService;
import com.niux.spring.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/24.下午3:26
 */
public class BeanServiceImpl implements BeanService {


    private DemoService demoService;


    public DemoService getDemoService() {
        return demoService;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public void getName(){

        demoService.test();

    }
}
