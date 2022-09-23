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

        List<Thread> threads = IntStream.range(1, 3).mapToObj(ThreadJoinDemo::create).collect(Collectors.toList());

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();

        }

    }

    private static Thread create(int seq) {
        return new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
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
