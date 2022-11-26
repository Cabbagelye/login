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
    private final static SimpleDateFormat simpleDateFormat_1 = new SimpleDateFormat("yyyy-MM-dd");

    private final static String serialNum = "100001";

    @Resource
    private TestLoginDao testLoginDao;


    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 注册账号
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
        String today = simpleDateFormat_1.format(new Date());

        User user = new User();
        user.setId(StringUtils.replace(String.valueOf(UUID.randomUUID()),"-",""));
        user.setName(signAccountDto.getName());
        //账号生成规则（日期 + 序号100001升序）
                //通过redis获取当天最大序号
        String accountSerialNum = (String)redisTemplate.opsForValue().get(LoginConstant.ACCOUNT_SERIAL_NUM + today);
        if(null == accountSerialNum){
            //校验数据库当天是否有注册账号,获取当天最后注册的序号
            String maxSerialNumByDay = testLoginDao.findMaxSerialNumByDay(today);
            if (null == maxSerialNumByDay){
                accountSerialNum = serialNum;
            }else {
                accountSerialNum = String.valueOf(Integer.valueOf(maxSerialNumByDay) + 1);
            }
        }else {
            accountSerialNum = String.valueOf(Integer.valueOf(accountSerialNum) + 1);
        }
        String account = simpleDateFormat.format(new Date()) + accountSerialNum;
        //密码（cabbage + 电话号码后4位）
        String password = "cabbage" + signAccountDto.getPhone().substring(signAccountDto.getPhone().length() - 4);
        user.setAccount(account);
        user.setPassword(password);
        user.setPhone(signAccountDto.getPhone());
        user.setEmail(signAccountDto.getEmail());
        user.setSerialNum(accountSerialNum);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        //插入账号
        int insert = testLoginDao.insert(user);
        if (insert > 0){
            redisTemplate.opsForValue().set(LoginConstant.ACCOUNT_SERIAL_NUM + today,accountSerialNum);
            return true;
        }else {
            return false;
        }
    }
}
