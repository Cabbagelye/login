package com.cabbagelye.thread.synch;

/**
 * @ClassName TicketWindowSynch
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/23 15:06
 **/
public class TicketWindowSynch implements Runnable{


    private int index = 1;

    private final static int MAX = 500;

    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX){
                System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowSynch task = new TicketWindowSynch();
        Thread windowThread1 = new Thread(task, "一号窗口");
        Thread windowThread2 = new Thread(task, "二号窗口");
        Thread windowThread3 = new Thread(task, "三号窗口");
        Thread windowThread4 = new Thread(task, "四号窗口");

        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
    }
}
