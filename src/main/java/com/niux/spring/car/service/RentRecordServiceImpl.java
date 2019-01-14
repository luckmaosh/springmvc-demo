package com.niux.spring.car.service;

import com.niux.spring.car.mapper.RentRecordMapper;
import com.niux.spring.car.model.RentRecord;
import com.niux.spring.car.model.RentRecordDO;
import org.apache.log4j.or.RendererMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RentRecordServiceImpl implements RentRecordService {

    @Autowired
    private RentRecordMapper rentRecordMapper;
    @Override
    public List<RentRecordDO> findMyRecord(long userId, int type) {

        RentRecord rentRecord = rentRecordMapper.selectByPrimaryKey(1L);
        return null;
    }
}
