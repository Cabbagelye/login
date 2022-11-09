package com.cabbagelye.login.tokenJwt.service;

import com.cabbagelye.login.tokenJwt.dao.TokenJwtLoginDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName TokenLoginService
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/15 10:00
 **/

@Service
public class TokenJwtLoginService {

    @Resource
    private TokenJwtLoginDao tokenLoginDao;
}
