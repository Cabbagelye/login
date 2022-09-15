package com.cabbagelye.login.token.service;

import com.cabbagelye.login.token.dao.TokenLoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName TokenLoginService
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/15 10:00
 **/

@Service
public class TokenLoginService {

    @Resource
    private TokenLoginDao tokenLoginDao;
}
