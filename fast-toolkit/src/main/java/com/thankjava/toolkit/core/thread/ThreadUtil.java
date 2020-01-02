package com.thankjava.toolkit.core.thread;

/**
 * 线程相关工具
 * <p>Function: ThreadUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年4月20日 下午3:01:40
 */
public class ThreadUtil {

    static final String DEFAULT_THREAD_NAME = "toolkit.ThreadUtil";

    /**
     * JVM退出会触发该动作
     * <p>Function: runWhenJVMExit</p>
     * <p>Description: </p>
     *
     * @param runnable
     * @author acexy@thankjava.com
     * @date 2016年1月5日 下午2:45:12
     */
    public static void runWhenJVMExit(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(ThreadPool.getNewThread(runnable));
    }
}
