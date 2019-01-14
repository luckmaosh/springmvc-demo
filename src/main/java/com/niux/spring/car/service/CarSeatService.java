package com.niux.spring.car.service;

import com.niux.spring.car.model.CarSeatDO;

import java.util.Date;
import java.util.List;

/**
 * 车位服务
 *
 * @author maso
 */
public interface CarSeatService {

    /**
     * 可能还需要状态，价格排序
     *
     * @param location
     * @param start
     * @param limit
     * @return
     */
    List<CarSeatDO> findCardSeat(String location, int start, int limit);

    /**
     * 发布车位出租
     *
     * @param userId
     * @param seatNo
     * @param price
     * @param begin
     * @param end
     * @return
     */
    long publish(long userId, String seatNo, String price, Date begin, Date end);

    /**
     * 成交
     *
     * @param userId
     * @param rentId
     * @param price
     * @param seatNo
     * @return
     */
    long deal(long userId, long rentId, String price, String seatNo);
}
