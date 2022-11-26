package com.cabbagelye.thread.api.ThreadClose;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadExitByInterrupt
 * @Description 捕获中断信号关闭线程
 * @Author Cabbagelye
 * @Date 2022/11/24 14:26
 **/
public class ThreadExitByInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("start work,in");
                while (!isInterrupted()){

                }
                System.out.println("end work,exit");
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("shutdown");
        thread.interrupt();
    }
}
