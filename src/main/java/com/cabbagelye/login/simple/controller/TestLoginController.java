package com.cabbagelye.login.simple.controller;

import com.cabbagelye.login.model.dto.SignAccountDto;
import com.cabbagelye.login.simple.service.TestLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private boolean signAccount(@RequestBody SignAccountDto signAccountDto){
        return testLoginService.signAccount(signAccountDto);
    }
}
