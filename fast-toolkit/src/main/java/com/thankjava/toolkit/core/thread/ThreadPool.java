package com.thankjava.toolkit.core.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadPool {

    /**
     * 核心线程保持数量
     */
    private static final int INIT_THREAD_NUM = 50;

    /**
     * 工作线程最大数量
     */
    private static final int MAX_THREAD_NUM = 10000;

    /**
     * 空闲线程最大等待时间
     */
    private static final int ALIVE_TIME = 60;

    /**
     * 排队任务接受最大数目
     */
    private static final int WAIT_RUNNABLE_MAX_NUM = 200;

    protected static String DEFAULT_THREAD_GROUP_NAME = "ThreadPool-Group";
    protected static String DEFAULT_THREAD_NAME = "Thread";

    private static ThreadPoolExecutor threadPoolExecutor;

    public ThreadPool() {
        threadPoolExecutor = new ThreadPoolExecutor(
                INIT_THREAD_NUM,
                MAX_THREAD_NUM,
                ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(WAIT_RUNNABLE_MAX_NUM),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * 指定线程池配置
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param initThreadNum      初始线程数量
     * @param maxThreadNum       最大线程数量
     * @param aliveTime          等待线程最长时间(ms)
     * @param waitRunnableMaxNum 接受排队线程最大数量
     */
    public ThreadPool(int initThreadNum, int maxThreadNum, int aliveTime, int waitRunnableMaxNum) {
        threadPoolExecutor = new ThreadPoolExecutor(
                initThreadNum,
                maxThreadNum,
                aliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(waitRunnableMaxNum),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    /**
     * 设置允许核心线程超时
     *
     * @return
     */
    public ThreadPool allowCoreThreadTimeOut() {
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return this;
    }

    /**
     * 设置线程组名，用于日志输出
     *
     * @param threadGroupName 线程组名
     * @param threadName      线程名
     * @return
     */
    public ThreadPool setThreadPoolName(String threadGroupName, String threadName) {
        DEFAULT_THREAD_GROUP_NAME = threadGroupName;
        DEFAULT_THREAD_NAME = threadName;
        threadPoolExecutor.setThreadFactory(new CustomThreadFactory());
        return this;
    }

    /**
     * 执行任务
     * <p>Function: execute</p>
     * <p>Description: </p>
     *
     * @param command
     * @author acexy@thankjava.com
     * @date 2016年1月5日 下午3:21:05
     */
    public void execute(Runnable command) {
        threadPoolExecutor.execute(command);
    }

    public void destroy() {
        if (!threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdown();
        }
    }

    class CustomThreadFactory implements ThreadFactory {

        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        CustomThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = DEFAULT_THREAD_GROUP_NAME + " " + poolNumber.getAndIncrement() + " ; " + DEFAULT_THREAD_NAME;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(group, runnable, namePrefix + " " + threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) thread.setDaemon(false);
            if (thread.getPriority() != Thread.NORM_PRIORITY) thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    }

}
