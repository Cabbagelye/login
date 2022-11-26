package com.cabbagelye.threadPool;

/**
 * @ClassName RunableDenyException
 * @Description 异常情况
 * @Author Cabbagelye
 * @Date 2022/11/24 17:10
 **/
public class RunableDenyException extends RuntimeException{

    public RunableDenyException(String message){
        super(message);
    }
}
