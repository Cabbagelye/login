package com.cabbagelye.threadPool;

/**
 * @InterFaceName RannableQueue
 * @Description 任务队列，主要用于缓存提交到线程池中的任务
 * @Author Cabbagelye
 * @Date 2022/11/24 16:42
 **/
public interface  RunnableQueue {

    /**
     * 新任务首先offer到队列中
     * @param runnable
     */
    void offer(Runnable runnable);

    /**
     * 工作线程通过take方法获取Runnable
     * @return
     */
    Runnable take();

    /**
     * 获取任务队列中任务的数量
     * @return
     */
    int size();
}
