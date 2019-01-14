package com.niux.spring.car.service;

import com.niux.spring.car.model.RentRecordDO;

import java.util.List;

/**
 * @author maso
 */
public interface RentRecordService {

    /**
     *
     * @param userId
     * @param type 出租1，承租2
     * @return
     */
    List<RentRecordDO> findMyRecord(long userId, int type);
}
