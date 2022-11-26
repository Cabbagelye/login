package com.cabbagelye.threadPool;

/**
 * @ClassName ThreadFactory
 * @Description 线程工厂
 * @Author Cabbagelye
 * @Date 2022/11/24 16:58
 **/
@FunctionalInterface
public interface ThreadFactory {
    /**
     * 创建线程
     * @param runnable
     * @return
     */
    Thread createThread(Runnable runnable);
}
