package com.cabbagelye.thread.api;

/**
 * @ClassName ThreadDemo2_setPriority
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/23 10:25
 **/
public class ThreadPriorityDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("t1");
            }
        });
        t1.setPriority(3);

        Thread t2 = new Thread(() -> {
            while (true) {
                System.out.println("t2");
            }
        });
        t2.setPriority(10);

        t1.start();
        t2.start();
        //以上代码执行结果可知，T2线程比T1线程的频率高；
    }

    public static void main2(String[] args) {
        ThreadGroup test_group = new ThreadGroup("test");
        //线程组最大优先级为7
        test_group.setMaxPriority(7);
        //定义一个线程，将线程加入线程组
        Thread thread = new Thread(test_group, "test_thread");
        //将线程优先级设置为10
        thread.setPriority(10);
        //获取当前线程优先级
        System.out.println(thread.getPriority());
        //以上代码执行结果可知，如果指定的线程优先级大于线程所在线程组的优先级，那么线程的优先级会失效；
    }
}
