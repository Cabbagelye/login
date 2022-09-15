package com.cabbagelye.login.oauth.service;


import com.cabbagelye.login.oauth.dao.OAuthDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OAuthService {

    @Resource
    private OAuthDao oAuthDao;
}
