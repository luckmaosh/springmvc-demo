package com.niux.spring.car;

import com.niux.spring.car.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    User selectByRoom(@Param("roomNo") String roomNo);

    User selectByMobile(@Param("mobile") String mobile);

    User selectBySeat(@Param("seat") String seat);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}