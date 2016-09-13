package org.thankjava.toolkit.thread;

/**
 * 线程相关工具
* <p>Function: ThreadUtil</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年4月20日 下午3:01:40
* @version 1.0
 */
public class ThreadUtil {

	/**
	 * JVM退出会触发该动作
	* <p>Function: runWhenJVMExit</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月5日 下午2:45:12
	* @version 1.0
	* @param thread
	 */
	public static void runWhenJVMExit(Runnable runnable) {
		Runtime.getRuntime().addShutdownHook(new Thread(runnable));
	}
}
