package com.cabbagelye.login.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @ClassName User
 * @Description TODO
 * @Authro Cabbagelye
 * @Date 2022/9/14 17:09
 **/

@TableName("user")
public class User {

    @TableId
    private String id;

    private String userName;

    private String passWord;
}
