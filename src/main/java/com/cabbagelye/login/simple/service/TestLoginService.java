package com.cabbagelye.login.simple.service;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import com.cabbagelye.login.common.LoginConstant;
import com.cabbagelye.login.model.User;
import com.cabbagelye.login.model.dto.SignAccountDto;
import com.cabbagelye.login.simple.dao.TestLoginDao;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TestLoginService {

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private final static String serialNum = "100001";

    @Resource
    private TestLoginDao testLoginDao;


    @Resource
    private RedisTemplate redisTemplate;

    /**
     *
     * @param signAccountDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean signAccount(SignAccountDto signAccountDto) {
        if (ObjectUtil.isNull(signAccountDto) ){
            return false;
        }
        if (null == signAccountDto.getName() || null == signAccountDto.getPhone() || null == signAccountDto.getEmail()){
            return false;
        }

        User user = new User();
        user.setId(StringUtils.replace(String.valueOf(UUID.randomUUID()),"-",""));
        user.setName(signAccountDto.getName());
        //账号生成规则（日期 + 序号100001升序）
        //通过redis获取当天最大序号
        String accountSerialNum = (String)redisTemplate.opsForValue().get(LoginConstant.ACCOUNT_SERIAL_NUM);
        String number = (null == accountSerialNum)?serialNum:accountSerialNum;
        int number0 = Integer.valueOf(serialNum) + 1;
        if (null != accountSerialNum){
            number0 = Integer.valueOf(accountSerialNum) + 1;
        }
        redisTemplate.opsForValue().set(LoginConstant.ACCOUNT_SERIAL_NUM,String.valueOf(number0));
        String account = simpleDateFormat.format(new Date()) + number;
        user.setAccount(account);
        user.setPhone(signAccountDto.getPhone());
        user.setEmail(signAccountDto.getEmail());
        //
        int insert = testLoginDao.insert(user);
        if (insert > 0){
            return true;
        }else {
            return false;
        }
    }
}
