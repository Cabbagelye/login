package com.cabbagelye.login.tokenJwt.controller;

import com.cabbagelye.login.model.dto.TokenLoginDto;
import com.cabbagelye.login.tokenJwt.service.TokenJwtLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @ClassName TokenLoginController
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/15 9:59
 **/

@Controller
@RequestMapping("/token")
public class TokenJwtLoginController {

    @Resource
    private TokenJwtLoginService tokenLoginService;


    @PostMapping("/getToken")
    private String getToken(@RequestBody TokenLoginDto tokenLoginDto){
        return "token";
    }


    @PostMapping("/login")
    private void login(String token){

    }
}
