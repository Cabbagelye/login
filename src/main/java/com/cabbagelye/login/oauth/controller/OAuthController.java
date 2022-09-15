package com.cabbagelye.login.oauth.controller;


import com.cabbagelye.login.oauth.service.OAuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/oauth")
public class OAuthController {

    @Resource
    private OAuthService oAuthService;
}
