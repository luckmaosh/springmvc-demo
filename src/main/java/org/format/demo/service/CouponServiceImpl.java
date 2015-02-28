package org.format.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by maso on 15/2/28.
 */

@Service
public class CouponServiceImpl implements CouponService {


    @Autowired
    private com.nuomi.coupon.service.CouponService couponService;

    @Override
    public String sayHello(String name) {
        return couponService.sayHello(name);
    }
}
