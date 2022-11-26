package com.cabbagelye.thread.api;

import java.util.stream.IntStream;

/**
 * @ClassName ThreadYieldDemo
 * @Description 线程放弃当前CPU资源，当前线程从Running状态进入Runnable状态
 * @Author Cabbagelye
 * @Date 2022/11/24 14:20
 **/
public class ThreadYieldDemo {

    public static void main(String[] args) {
        IntStream.range(0,2).mapToObj(ThreadYieldDemo::create).forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(()->{
            if (index == 0){
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
