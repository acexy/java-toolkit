package thread;

import java.io.IOException;

import org.thankjava.toolkit.thread.ThreadUtil;
import org.thankjava.toolkit.vo.thread.TaskEntity;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		ThreadUtil.runWhenJVMExit(new Runnable() {
			@Override
			public void run() {
				System.out.println("主线程停止");
			}
		});

//		ThreadTask threadTask = new ThreadTask(10);
//		TaskEntity task1 = task1();
//		threadTask.addTask(task1);
//		System.out.println("加入任务1");
//		System.out.println("运行中的任务数量: " + threadTask.getRunningTaskCount());
//
//		Thread.sleep(3000);
//		System.out.println("线程休息3秒后 加入任务2");
//		threadTask.addTask(task2());
//
//		System.out.println("运行中的任务数量: " + threadTask.getRunningTaskCount());
//
//		Thread.sleep(5000);
//		System.out.println("线程休息5秒后 移除任务1 flag: " + threadTask.removeTaskByTaskId(task1.getTaskId(), false));
//		System.out.println("运行中的任务数量: " + threadTask.getRunningTaskCount());
//
//		Thread.sleep(2000);
//		task1 = task1();
//		threadTask.addTask(task1);
//		System.out.println("线程休息2秒后 加入任务1");
//		System.out.println("运行中的任务数量: " + threadTask.getRunningTaskCount());
//
//		Thread.sleep(5000);
//		threadTask.clearAllTasks(false);
//		System.out.println("线程休息5秒后 移除所有任务");
//		System.out.println("运行中的任务数量: " + threadTask.getRunningTaskCount());
//
//		Thread.sleep(5000);
//		System.out.println("线程休息5秒后停止任务服务");
//		threadTask.shutdown();

		// Runtime runtime = Runtime.getRuntime();
		// Process p = runtime.exec("cd F:");
		// p.waitFor();
		// InputStream is = p.getInputStream();
		// byte[] bts = new byte[is.available()];
		// is.read(bts);
		// is.close();
		// System.out.println(new String(bts, "GBK"));
		//
		// p = runtime.exec("cd ../");
		// p.waitFor();
		// is = p.getInputStream();
		// bts = new byte[is.available()];
		// is.read(bts);
		// is.close();
		// System.out.println(new String(bts, "GBK"));
		//
		//
		// p = runtime.exec("pwd");
		// p.waitFor();
		// is = p.getInputStream();
		// bts = new byte[is.available()];
		// is.read(bts);
		// is.close();
		// System.out.println(new String(bts, "GBK"));

		
//		ThreadTask threadTask = new ThreadTask(10);
//		threadTask.addTaskRunOnce(new Runnable() {
//			public void run() {
//				for (int i = 0; i < 10; i++) {
//					try {
//						Thread.sleep(1000);
//						System.out.println(i);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		});
	}

	private static TaskEntity task1() {
		return new TaskEntity(0, 0, new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(1000);
						System.out.println("task 1 running" + i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
//				System.out.println("task 1 running");
			}
		});
	}

	private static TaskEntity task2() {
		return new TaskEntity(1, 1, new Runnable() {
			@Override
			public void run() {
				System.out.println("task 2 running");
			}
		});
	}
}
