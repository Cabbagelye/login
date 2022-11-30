package com.cabbagelye.login.simple.controller;

import com.cabbagelye.login.model.po.User;
import com.cabbagelye.login.model.dto.SignAccountDto;
import com.cabbagelye.login.simple.service.TestLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("testLogin")
public class TestLoginController {


    @Resource
    private TestLoginService testLoginService;

    /**
     *
     * @param signAccountDto
     * @return
     */
    @PostMapping("/signAccount")
    @ResponseBody
    private String signAccount(@RequestBody SignAccountDto signAccountDto){
        return testLoginService.signAccount(signAccountDto);
    }

    /**
     * 根据账号获取用户信息
     * @param account
     * @return
     */
    @PostMapping("/getUserInfo")
    @ResponseBody
    private User getUserInfo(@RequestBody String account){
        if (null == account){
            throw new RuntimeException("登录账号不能为空");
        }
        return testLoginService.getUserInfo(account);
    }
}
