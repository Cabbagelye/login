package com.cabbagelye.threadPool;

import java.util.LinkedList;

/**
 * @ClassName LinkedRunnableQueue
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/11/25 9:38
 **/
public class LinkedRunnableQueue implements RunnableQueue{

    private final int limit;

    private final DenyPolicy denyPolicy;

    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool) {
        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }

    /**
     * offer 同步方法，队列数据达到上限，执行拒绝策略；反之放入任务队列中，唤醒线程
     * @param runnable
     */
    @Override
    public void offer(Runnable runnable) {
        synchronized (runnableList){
            if (runnableList.size() >= limit){
                //执行拒绝策略，抛弃新的任务
                denyPolicy.reject(runnable,threadPool);
            }else {
                //将任务加入队列队尾，并唤醒阻塞中的线程
                runnableList.add(runnable);
                runnableList.notifyAll();
            }
        }
    }

    /**
     * 同步方法，队列为空时工作线程进入阻塞状态
     * @return
     * @throws InterruptedException
     */
    @Override
    public Runnable take() throws InterruptedException {
        synchronized (runnableList){
            while (runnableList.isEmpty()){
                try {
                    runnableList.wait();
                }catch (InterruptedException e){
                    throw e;
                }
            }
            return runnableList.removeFirst();
        }
    }

    @Override
    public int size() {
        synchronized (runnableList){
            //返回当前任务队列中的线程数
            return runnableList.size();
        }
    }
}
