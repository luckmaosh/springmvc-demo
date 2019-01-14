package com.niux.spring.car.service;

import com.niux.spring.car.mapper.CarSeatMapper;
import com.niux.spring.car.model.CarSeatDO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CarSeatServiceImpl implements CarSeatService {

    @Autowired
    private CarSeatMapper carSeatMapper;


    @Override
    public List<CarSeatDO> findCardSeat(String location, int start, int limit) {
        return null;
    }

    @Override
    public long publish(long userId, String seatNo, String price, Date begin, Date end) {
        return 0;
    }

    @Override
    public long deal(long userId, long rentId, String price, String seatNo) {
        return 0;
    }
}
