package org.thankjava.toolkit.vo.thread;

import org.thankjava.toolkit.radom.Sequence;

public class TaskEntity {

	/**
	 * 任务id
	 */
	private String taskId;
	
	/**
	 * 任务间隔时间
	 */
	private int timeInterval = 0;
	
	/**
	 * 任务启动延迟时间
	 */
	private int startDelayTime = 0;
	
	/**
	 * 任务执行体
	 */
	private Runnable runnable;

	/**
	 * 初始化对象
	 * @param startDelayTime
	 * @param timeInterval 间隔时间不能小于0 （秒）
	 * @param runnable
	 */
	public TaskEntity(int startDelayTime, int timeInterval, Runnable runnable){
		this.taskId = Sequence.generateChar(6);
		this.timeInterval = timeInterval;
		this.runnable = runnable;
		this.startDelayTime = startDelayTime;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public int getStartDelayTime() {
		return startDelayTime;
	}

	public Runnable getRunnable() {
		return runnable;
	}
	
	

}
