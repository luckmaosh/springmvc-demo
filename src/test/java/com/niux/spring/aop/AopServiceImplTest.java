package com.niux.spring.aop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aop-spring.xml"})
public class AopServiceImplTest {

    @Autowired
    private IAopService aopService;


    @Test
    public void name() {


        try {
            aopService.withAop();

            aopService.withoutAop();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}