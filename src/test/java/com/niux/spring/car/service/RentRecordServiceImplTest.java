package com.niux.spring.car.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RentRecordServiceImplTest {

    @Autowired
    private RentRecordService rentRecordService;

    @Test
    public void name() {

        rentRecordService.findMyRecord(1,1);
    }
}