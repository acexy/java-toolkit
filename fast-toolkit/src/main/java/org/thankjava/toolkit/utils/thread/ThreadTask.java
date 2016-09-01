package org.thankjava.toolkit.utils.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.thankjava.toolkit.vo.thread.TaskEntity;

public final class ThreadTask {

	private ScheduledExecutorService scheduledExecutorService;

	private ConcurrentHashMap<String, ScheduledFuture<?>> runningTask = new ConcurrentHashMap<String, ScheduledFuture<?>>();
	
	/**
	 * 初始化定时服务
	* <p>Title: </p>
	* <p>Description: </p>
	* @param poolSize 初始化的核心线程数量
	 */
	public ThreadTask (int poolSize){
		scheduledExecutorService = Executors.newScheduledThreadPool(poolSize);
	}
	
	/**
	 * 添加任务
	* <p>Function: addTask</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:39:12
	* @version 1.0
	* @param taskEntity
	 */
	public void addTask(TaskEntity taskEntity){
		ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(
				taskEntity.getRunnable(), 
				taskEntity.getStartDelayTime(), 
				taskEntity.getTimeInterval(), 
				TimeUnit.SECONDS
				);
		runningTask.put(taskEntity.getTaskId(), future);
	}
	
	/**
	 * 添加批量任务
	* <p>Function: addTask</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:39:53
	* @version 1.0
	* @param taskEntitys
	 */
	public void addTask(List<TaskEntity> taskEntitys){
		for (TaskEntity taskEntity : taskEntitys) {
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
	 * 只执行一次
	 * @param runnable
	 */
	public void addTaskRunOnce(Runnable runnable){
		TaskEntity taskEntity = new TaskEntity(0, Integer.MAX_VALUE, runnable);
		scheduledExecutorService.scheduleAtFixedRate(
				taskEntity.getRunnable(), 
				taskEntity.getStartDelayTime(), 
				taskEntity.getTimeInterval(), 
				TimeUnit.SECONDS
				);
	}
	
	public void addTaskRunOnce(int startDelayTime,Runnable runnable){
		TaskEntity taskEntity = new TaskEntity(startDelayTime, Integer.MAX_VALUE, runnable);
		scheduledExecutorService.scheduleAtFixedRate(
				taskEntity.getRunnable(), 
				taskEntity.getStartDelayTime(), 
				taskEntity.getTimeInterval(), 
				TimeUnit.SECONDS
				);
		
	}
	
	/**
	 * 通过任务id 停止某个任务
	* <p>Function: removeTaskByTaskId</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:40:04
	* @version 1.0
	* @param taskId
	* @param isInterrupt 是否要强制中断该任务（如果任务正在进行）
	 */
	public boolean removeTaskByTaskId(String taskId,boolean isInterrupt){
		ScheduledFuture<?> future = runningTask.get(taskId);
		boolean flag = future.cancel(isInterrupt);
		if(flag){
			runningTask.remove(taskId);
		}
		return flag;
	}
	
	/**
	 * 获取运行中的任务数量
	* <p>Function: getRunningTaskCount</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:40:59
	* @version 1.0
	* @return
	 */
	public int getRunningTaskCount(){
		return runningTask.size();
	}
	
	/**
	 * 清除所有任务
	* <p>Function: clearAllTasks</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 下午3:33:29
	* @version 1.0
	* @param isInterrupt
	 */
	public void clearAllTasks(boolean isInterrupt){
		List<String> taskIds = new ArrayList<String>();
		for (Map.Entry<String, ScheduledFuture<?>> tasks : runningTask.entrySet()) {
			ScheduledFuture<?> future = runningTask.get(tasks.getKey());
			boolean flag = future.cancel(isInterrupt);
			if(flag){
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
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:47:21
	* @version 1.0
	 */
	public void shutdown(){
		if(scheduledExecutorService != null){
			scheduledExecutorService.shutdown();
		}
	}
}
