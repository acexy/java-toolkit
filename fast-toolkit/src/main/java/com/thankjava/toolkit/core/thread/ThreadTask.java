package com.thankjava.toolkit.core.thread;

import com.thankjava.toolkit.bean.thread.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public final class ThreadTask {

    private ScheduledExecutorService scheduledExecutorService;

    private ConcurrentHashMap<String, ScheduledFuture<?>> runningTask = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    /**
     * 初始化定时服务
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param poolSize 初始化的核心线程数量
     */
    public ThreadTask(int poolSize) {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(100, new ThreadFactory() {

            private final AtomicInteger poolNumber = new AtomicInteger(1);
            private final AtomicInteger threadNumber = new AtomicInteger(1);

            SecurityManager s = System.getSecurityManager();
            private final ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            private String namePrefix = "toolkit.Task";
            private String DEFAULT_THREAD_GROUP_NAME = "toolkit.Task-Group";
            private String DEFAULT_THREAD_NAME = "toolkit.Task";

            @Override
            public Thread newThread(Runnable runnable) {

                namePrefix = DEFAULT_THREAD_GROUP_NAME + " " + poolNumber.getAndIncrement() + " ; " + DEFAULT_THREAD_NAME;

                Thread thread = new Thread(group, runnable, namePrefix + " " + threadNumber.getAndIncrement(), 0);
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }

                if (thread.getPriority() != Thread.NORM_PRIORITY) {
                    thread.setPriority(Thread.NORM_PRIORITY);
                }

                return thread;
            }

        });
    }

    /**
     * 添加任务 该任务将在指定首次延迟时间之后周期循环
     * <p>Function: addTask</p>
     * <p>
     * Description: 如果任务的任何执行遇到异常,则抑制后续的执行,否则,任务只会通过执行器的取消或终止而终止
     * 1.当任务周期过长时，下一个任务就算到达执行时间也将会处于等待状态,直到上一个任务完成后将立刻执行
     * 2.下一个任务的执行时间并不是上一个任务完成后才计算指定的周期延时时间,而是上一个任务一开始,下一个任务的延迟时间就开始计算
     * </p>
     *
     * @param taskEntity
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:39:12
     */
    public void addTaskAtFixedRate(TaskEntity taskEntity) {
        ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(
                taskEntity.getRunnable(),
                taskEntity.getStartDelayTime(),
                taskEntity.getTimeInterval(),
                TimeUnit.SECONDS
        );
        runningTask.put(taskEntity.getTaskId(), future);
    }

    /**
     * 添加批量任务 该任务将在指定首次延迟时间之后周期循环
     * <p>Function: addTask</p>
     * <p>
     * Description: 如果任务的任何执行遇到异常,则抑制后续的执行,否则,任务只会通过执行器的取消或终止而终止
     * 1.当任务周期过长时，下一个任务就算到达执行时间也将会处于等待状态,直到上一个任务完成后将立刻执行
     * 2.下一个任务的执行时间并不是上一个任务完成后才计算指定的周期延时时间,而是上一个任务一开始,下一个任务的延迟时间就开始计算
     * </p>
     *
     * @param taskEntities
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:39:12
     */
    public void addTaskAtFixedRate(List<TaskEntity> taskEntities) {
        for (TaskEntity taskEntity : taskEntities) {
            ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(
                    taskEntity.getRunnable(),
                    taskEntity.getStartDelayTime(),
                    taskEntity.getTimeInterval(),
                    TimeUnit.SECONDS
            );
            runningTask.put(taskEntity.getTaskId(), future);
        }
    }

    /**
     * 添加任务 该任务将在指定首次延迟时间之后按照指定频率固定周期循环
     * <p>Function: addTaskWithFixedDelay</p>
     * <p>
     * Description: 如果任务的任何执行遇到异常,则抑制后续的执行,否则,任务只会通过执行器的取消或终止而终止
     * 1.当任务周期过长时，下一个任务就算到达执行时间也将会处于等待状态,直到上一个任务完成后才计算周期延迟时间
     * </p>
     *
     * @param taskEntity
     * @author acexy@thankjava.com
     * @date 2016年10月8日 下午3:24:48
     */
    public void addTaskWithFixedDelay(TaskEntity taskEntity) {
        ScheduledFuture<?> future = scheduledExecutorService.scheduleWithFixedDelay(
                taskEntity.getRunnable(),
                taskEntity.getStartDelayTime(),
                taskEntity.getTimeInterval(),
                TimeUnit.SECONDS
        );
        runningTask.put(taskEntity.getTaskId(), future);
    }

    /**
     * 添加批量任务 该任务将在指定首次延迟时间之后周期循环
     * <p>Function: addTask</p>
     * <p>
     * Description: 如果任务的任何执行遇到异常,则抑制后续的执行,否则,任务只会通过执行器的取消或终止而终止
     * 1.当任务周期过长时，下一个任务就算到达执行时间也将会处于等待状态,直到上一个任务完成后才计算周期延迟时间
     * </p>
     *
     * @param taskEntities
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:39:12
     */
    public void addTaskWithFixedDelay(List<TaskEntity> taskEntities) {
        for (TaskEntity taskEntity : taskEntities) {
            ScheduledFuture<?> future = scheduledExecutorService.scheduleWithFixedDelay(
                    taskEntity.getRunnable(),
                    taskEntity.getStartDelayTime(),
                    taskEntity.getTimeInterval(),
                    TimeUnit.SECONDS
            );
            runningTask.put(taskEntity.getTaskId(), future);
        }
    }


    /**
     * 运行一次的指定任务
     * <p>Function: addTaskRunOnce</p>
     * <p>Description: </p>
     *
     * @param startDelayTime 延迟时间(s)
     * @param runnable
     * @author acexy@thankjava.com
     * @date 2016年10月8日 下午3:00:51
     */
    public void addTaskRunOnce(int startDelayTime, Runnable runnable) {
        scheduledExecutorService.schedule(runnable, startDelayTime, TimeUnit.SECONDS);
    }

    /**
     * 通过任务id 停止某个任务
     * <p>Function: removeTaskByTaskId</p>
     * <p>Description: </p>
     *
     * @param taskId
     * @param isInterrupt 是否要强制中断该任务（如果任务正在进行）
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:40:04
     */
    public boolean removeTaskByTaskId(String taskId, boolean isInterrupt) {
        ScheduledFuture<?> future = runningTask.get(taskId);
        boolean flag = future.cancel(isInterrupt);
        if (flag) {
            runningTask.remove(taskId);
        }
        return flag;
    }

    /**
     * 获取运行中的任务数量
     * <p>Function: getRunningTaskCount</p>
     * <p>Description: </p>
     *
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:40:59
     */
    public int getRunningTaskCount() {
        return runningTask.size();
    }

    /**
     * 清除所有任务
     * <p>Function: clearAllTasks</p>
     * <p>Description: </p>
     *
     * @param isInterrupt
     * @author acexy@thankjava.com
     * @date 2016年1月12日 下午3:33:29
     */
    public void clearAllTasks(boolean isInterrupt) {
        List<String> taskIds = new ArrayList<String>();
        for (Map.Entry<String, ScheduledFuture<?>> tasks : runningTask.entrySet()) {
            ScheduledFuture<?> future = runningTask.get(tasks.getKey());
            boolean flag = future.cancel(isInterrupt);
            if (flag) {
                taskIds.add(tasks.getKey());
            }
        }
        for (String taskId : taskIds) {
            runningTask.remove(taskId);
        }

    }

    /**
     * 停止整个任务服务
     * <p>Function: shutdown</p>
     * <p>Description: </p>
     *
     * @author acexy@thankjava.com
     * @date 2016年1月12日 上午11:47:21
     */
    public void shutdown() {
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdown();
        }
    }
}
