package com.cabbagelye.threadPool;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadPoolTest
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/11/25 11:51
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        final BasicThreadPool threadPool = new BasicThreadPool(2, 6, 4, 10);
        for (int i = 0; i < 6; i++) {
            threadPool.execute(()->{
                try{
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (;;){
            System.out.println("getActiveCount:" + threadPool.getActiveCount());
            System.out.println("getQueueSize:" + threadPool.getQueueSize());
            System.out.println("getCoreSize:" + threadPool.getCoreSize());
            System.out.println("getMaxSize:" + threadPool.getMaxSize());
            System.out.println("========================================");
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
