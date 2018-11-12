package thread;

import com.thankjava.toolkit.core.thread.ThreadPool;

public class ThreadPoolTask {


    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.setThreadPoolName("testGroup","testThread");
        while (true) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getThreadGroup().getName());
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("do");
                }
            });
            Thread.sleep(2);
        }
    }
}
