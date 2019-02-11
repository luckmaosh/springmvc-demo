package com.niux.spring.car.service;

import com.niux.spring.car.UserMapper;
import com.niux.spring.car.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public long regiter(String name, String mobile, String roomNo, String cardSeatNo) {
        if (StringUtils.isNotBlank(name) &&
            StringUtils.isNotBlank(mobile) &&
            StringUtils.isNotBlank(roomNo)
            ) {

            User selectByRoom = userMapper.selectByRoom(roomNo);
            if (selectByRoom != null) {
                return -2;
            }

            if (StringUtils.isNotBlank(cardSeatNo)) {
                if (userMapper.selectBySeat(cardSeatNo) != null) {
                    return -2;
                }
            }


            User user = new User();
            user.setCity("杭州");
            user.setGmtCreate(new Date());
            user.setGmtModified(new Date());
            user.setOwner(name);
            user.setMobile(mobile);
            user.setRoomNo(roomNo);
            user.setSeatNo(cardSeatNo);
            int insert = userMapper.insert(user);
            return insert;
        } else {
            return -1;
        }

    }
}
