package com.niux.spring.car.mapper;

import com.niux.spring.car.model.CarSeat;

public interface CarSeatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CarSeat record);

    int insertSelective(CarSeat record);

    CarSeat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CarSeat record);

    int updateByPrimaryKey(CarSeat record);
}