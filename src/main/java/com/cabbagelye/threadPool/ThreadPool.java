package com.cabbagelye.threadPool;

/**
 * @InterFaceName ThreadPool
 * @Description 自定义线程池方法
 * @Author Cabbagelye
 * @Date 2022/11/24 16:37
 **/
public interface ThreadPool {

    /**
     * 提交任务到线程池
     * @param runnable
     */
    void execute(Runnable runnable);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 获取线程池的初始化大小
     */
    int getInitSize();

    /**
     * 获取线程池最大线程数
     */
    int getMaxSize();

    /**
     * 获取线程池核心线程数
     */
    int getCoreSize();

    /**
     * 获取线程池中用于缓存任务队列的大小
     */
    int getQueueSize();

    /**
     * 获取线程池中活跃线程的数量
     */
    int getActiveCount();

    /**
     * 线程池是否已关闭
     */
    boolean isShutdown();
}
