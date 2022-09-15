package com.cabbagelye.login.simple.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cabbagelye.login.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SimpleLoginDao extends BaseMapper<User> {

    /**
     *
     * @param username
     * @param password
     * @return
     */
    List<User> login(@Param("username") String username, @Param("password") String password);
}
