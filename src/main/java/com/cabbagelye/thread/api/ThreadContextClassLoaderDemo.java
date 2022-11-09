package com.cabbagelye.thread.api;

/**
 * @ClassName ThreadContextClassLoader
 * @Description TODO
 * @Author Cabbagelye
 * @Date 2022/9/23 11:03
 **/
public class ThreadContextClassLoaderDemo {

    public static void main(String[] args) {
        //getContextClassLoader()：获取线程上下文的类加载器，即当前线程由哪个类加载器加载；如果没有修改线程上下文类加载器的情况下，保持与父类线程同样的类加载器
        ClassLoader contextClassLoader = new Thread().getContextClassLoader();
        System.out.println(contextClassLoader.getClass().getName());
    }
}
