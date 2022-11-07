package com.cabbagelye.login.simple.controller;

import com.cabbagelye.login.model.vo.StudentVo;
import com.cabbagelye.login.simple.service.SimpleLoginService;
import com.cabbagelye.login.model.dto.LoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description TODO
 * @Authro Cabbagelye
 * @Date 2022/9/14 16:58
 **/

@Controller
@RequestMapping("/simple")
public class SimpleLoginController {

    @Resource
    private SimpleLoginService simpleLoginService;


    /**
     * 简单登录
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    private String login(@RequestBody LoginDto loginDto){
        boolean result = simpleLoginService.login(loginDto.getUsername(),loginDto.getPassword());
        if (result){
            return "登录成功......";
        }
        return "登录失败......";
    }

    /**
     *
     * @return
     */
    @PostMapping("/getStudentInfo")
    @ResponseBody
    private StudentVo getStudentInfo(@RequestBody String stuNo){
        StudentVo studentVo = simpleLoginService.getStudentInfo(stuNo);
        return studentVo;
    }

}
