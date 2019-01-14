package com.niux.spring.car.service;

public interface UserService {

    /**
     * z注册
     * @param name
     * @param mobile
     * @param roomNo
     * @param cardSeatNo  选填
     * @return
     */
    long regiter(String name, String mobile, String roomNo, String cardSeatNo);
}
