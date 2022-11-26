package com.cabbagelye.threadPool;

/**
 * @InterFaceName DenyPolicy
 * @Description 拒绝策略
 * @Author Cabbagelye
 * @Date 2022/11/24 17:00
 **/
public interface DenyPolicy {

    /**
     * 拒绝方法
     * @param runnable
     * @param threadPool
     */
    void reject(Runnable runnable,ThreadPool threadPool);

    /**
     * 拒绝策略——直接丢弃掉Runnable任务
     */
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            throw new RuntimeException("THE RUNNABLE" + runnable + "WILL BE ABORT");
        }
    }

    /**
     * 拒绝策略——交给调用者的线程直接执行Runnable，而不会被加入线程池
     */
    class RunnableDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if (!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }


}
