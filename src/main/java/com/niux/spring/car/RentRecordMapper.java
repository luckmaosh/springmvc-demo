package com.niux.spring.car;

import com.niux.spring.car.model.RentRecord;

public interface RentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RentRecord record);

    int insertSelective(RentRecord record);

    RentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RentRecord record);

    int updateByPrimaryKey(RentRecord record);
}