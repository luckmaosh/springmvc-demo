package com.niux.spring.car.service;

import com.niux.spring.car.mapper.CarSeatMapper;
import com.niux.spring.car.model.CarSeat;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CarSeatServiceImpl implements CarSeatService {

    @Autowired
    private CarSeatMapper carSeatMapper;

    @Override
    public List<CarSeat> findCardSeat(String location, int start, int limit) {
        if (location != null && location.length() > 0) {
            List<CarSeat> carSeats = carSeatMapper.find(location, start, limit);

            return carSeats;
        }
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
