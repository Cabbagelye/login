package com.cabbagelye.threadPool;

/**
 * @ClassName InternalTask
 * @Description 从queue中取出runnable
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
                Runnable runnable = runnableQueue.take();
                runnable.run();
            }catch (RunableDenyException | InterruptedException e){
                running = false;
                break;
            }
        }
    }

    public void stop(){
        this.running = false;
    }
}
