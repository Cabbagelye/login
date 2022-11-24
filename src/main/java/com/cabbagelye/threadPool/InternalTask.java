package com.cabbagelye.threadPool;

/**
 * @ClassName InternalTask
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/11/24 17:12
 **/
public class InternalTask implements Runnable{

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        while (running && !Thread.currentThread().isInterrupted()){
            try {
                Runnable take = runnableQueue.take();
                take.run();
            }catch (RunableDenyException e){
                running = false;
                break;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
