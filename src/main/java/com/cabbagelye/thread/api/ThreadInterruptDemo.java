package com.cabbagelye.thread.api;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadInterruptDemo
 * @Description 打断线程阻塞
 * @Author Cabbagelye
 * @Date 2022/9/23 11:15
 **/
public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("被interrupt中断阻塞");
                e.printStackTrace();
            }
        });
        thread.start();

        //
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
