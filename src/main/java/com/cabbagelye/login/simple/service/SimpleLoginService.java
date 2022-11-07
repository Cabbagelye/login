package com.cabbagelye.login.simple.service;

import com.cabbagelye.login.model.User;
import com.cabbagelye.login.model.vo.StudentVo;
import com.cabbagelye.login.simple.dao.SimpleLoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName SimpleLoginService
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/14 17:00
 **/

@Service
public class SimpleLoginService {

    @Resource
    private SimpleLoginDao simpleLoginDao;

    /**
     * 简单登录
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        List<User> login = simpleLoginDao.login(username, password);
        if (login.size() == 1){
            return true;
        }
        return false;
    }


    /**
     *
     * @param stuNo
     * @return
     */
    public StudentVo getStudentInfo(String stuNo) {
        return simpleLoginDao.getStudentInfo(stuNo);
    }
}
