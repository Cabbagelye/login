package com.cabbagelye.thread.api;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @ClassName ThreadJoinDemo
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/23 11:56
 **/
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {

        //join()：join某个线程A，会使当前线程B进入等待，直到线程A结束生命周期或达到一定的时间；此时线程B处于BLOCKED状态；

        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoinDemo::create).collect(Collectors.toList());
        //启动线程
        threads.forEach(Thread::start);
        //循环执行线程的join方法
        //join方法是被主线程调用的，当第一个线程还没有结束生命周期的时候，第二个线程的join不会得到执行
        for (Thread thread : threads) {
            thread.join();
        }
        //main方法线程循环输出
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }

    }

    private static Thread create(int seq) {
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "_create_" + i);
                shortSleep();
            }
        });
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
