package com.niux.spring.aop;

import com.niux.spring.aop2.Aop2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:aop-spring.xml"})
public class AopServiceImplTest {

    @Autowired
    private Aop2Service aopService;

    @Autowired
    private Aop2Service aop2Service;

    @Test
    public void test2() throws Exception {
            aop2Service.withAop2();


    }

    @Test
    public void name() {


        try {
            aopService.withAop2();

            aopService.withoutAop();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}