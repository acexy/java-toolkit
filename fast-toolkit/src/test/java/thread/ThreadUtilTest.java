package thread;

import com.thankjava.toolkit.core.thread.ThreadUtil;

/**
 * @Desc: TODO
 * @Author: acexy@thankjava.com
 * @Date: 2019/12/31
 **/
public class ThreadUtilTest {
    public static void main(String[] args) throws InterruptedException {

        Thread.sleep(1000);

        ThreadUtil.runWhenJVMExit(new Runnable() {
            @Override
            public void run() {
                System.out.println("jvm shutdown");
            }
        });
    }
}
