package com.cabbagelye.thread.api.ThreadClose;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadExitByVolatile
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/11/24 14:36
 **/
public class ThreadExitByVolatile {


    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        myTask.start();
        System.out.println("sleep.....");
        TimeUnit.SECONDS.sleep(5);
        System.out.println("shutdown.....");
        myTask.close();
        System.out.println("close......");
    }

    static class MyTask extends Thread{
        private volatile boolean closed = false;

        @Override
        public void run() {
            System.out.println("start work,in");
            while(!closed && !isInterrupted()){

            }
            System.out.println("end work,exit");
        }

        public void close(){
            this.closed = true;
            this.interrupt();
        }
    }


}
