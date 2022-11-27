package com.cabbagelye.login.simple.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cabbagelye.login.model.User;
import org.apache.ibatis.annotations.Param;

public interface TestLoginDao extends BaseMapper<User> {


    /**
     * 获取当天最后注册的序号
     * @param today
     * @return
     */
    String findMaxSerialNumByDay(@Param("today") String today);


    /**
     * 根据账号获取用户信息
     * @param account
     * @return
     */
    User getUserInfo(@Param("account") String account);
}
