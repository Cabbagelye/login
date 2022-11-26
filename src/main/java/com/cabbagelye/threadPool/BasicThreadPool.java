package com.cabbagelye.threadPool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName BasicThreadPool
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/11/25 9:53
 **/
public class BasicThreadPool extends Thread implements ThreadPool{

    private int activeCount;

    private final int initSize;

    private final int maxSize;

    private final int coreSize;

    private final ThreadFactory threadFactory;

    private final RunnableQueue runnableQueue;

    private volatile boolean isShutdown = false;

    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;


    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize) {
        this(initSize,maxSize,coreSize,DEFAULT_THREAD_FACTORY,queueSize,DEFAULT_DENY_POLICY,10,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize, ThreadFactory threadFactory,int queueSize,DenyPolicy denyPolicy,long keepAliveTime,TimeUnit timeUnit ) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize,denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    /**
     *
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if (this.isShutdown){
            throw new IllegalArgumentException("");
        }
        this.runnableQueue.offer(runnable);
    }

    /**
     *
     */
    @Override
    public void shutdown() {
        synchronized (this){
            if (isShutdown) {return;}
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown){
            throw new IllegalArgumentException("");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown){
            throw new IllegalArgumentException("");
        }
        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown){
            throw new IllegalArgumentException("");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown){
            throw new IllegalArgumentException("");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        if (isShutdown){
            throw new IllegalArgumentException("");
        }
        return this.activeCount;
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }


    /**
     *
     */
    @Override
    public void run(){
        while (!isShutdown && !isInterrupted()){
            try{
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }

            synchronized (this){
                if (isShutdown){
                    break;
                }
                //判定当前队列中是否有任务尚未处理，并且activeCount < coreSize，则扩容
                if (runnableQueue.size() > 0 && activeCount < coreSize){
                    for (int i = initSize; i < coreSize; i++) {
                        newThread();
                    }
                    //防止线程扩容达到线程池最大线程数
                    continue;
                }

                //判定当前队列中是否有任务尚未处理，并且activeCount < maxSize，则扩容
                if (runnableQueue.size() > 0 && activeCount < maxSize){
                    for (int i = coreSize; i < maxSize; i++) {
                        newThread();
                    }
                }

                //判定当前队列中是否有任务尚未处理，没有则回收至coreSize大小
                if (runnableQueue.size() == 0 && activeCount > coreSize){
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    /**
     *
     */
    private void init() {
        start();
        for (int i = 0; i < initSize; i++) {
            new Thread();
        }
    }

    /**
     * 创建线程
     */
    public void newThread() {
        //创建任务线程，并启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        activeCount = this.getActiveCount();
        activeCount ++;
        thread.start();
    }

    /**
     * 移除线程
     */
    public void removeThread() {
        //异常线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        activeCount = this.getActiveCount();
        activeCount --;
    }

    /**
     * 组合Thread、InternalTask，创建线程并从队列中获取runnable
     */
    private static class ThreadTask {
        public ThreadTask(Thread thread,InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
        Thread thread;
        InternalTask internalTask;
    }

    /**
     * 创建线程
     */
    private static class DefaultThreadFactory implements ThreadFactory{

        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());
        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group,runnable,"thread-pool-" + COUNTER.getAndDecrement());
        }
    }
}