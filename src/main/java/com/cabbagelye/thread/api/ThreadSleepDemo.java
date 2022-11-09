package com.cabbagelye.thread.api;

/**
 * @ClassName ThreadDemo
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/22 14:32
 **/
public class ThreadSleepDemo {

    public static void main(String[] args) {
        new Thread(()->{
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("total spend %d ms",(endTime - startTime)));
        }).start();
    }


    private static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
