package db.redis;

import com.thankjava.toolkit.core.thread.ThreadPool;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;
import com.thankjava.toolkit3d.core.db.redis.datasource.RedisManagerImpl;

public class RedisManagerTest {


    static ThreadPool pool = new ThreadPool();

    public static void main(String[] args) throws InterruptedException {
//        final RedisManager redis = RedisManagerImpl.getSingleton();
		final RedisManager redis = RedisManagerImpl.getSingleton("/Users/acexy/Development/config/acexy/java-toolkit/redis.properties");

        // 等待redis初始化完成
        Thread.currentThread().sleep(3000);

        for (int i = 0; i < 1000; i++) {
            pool.execute(new Runnable() {

                @Override
                public void run() {
                    redis.incr("a");
                    System.out.println("do");
                }
            });
        }
    }

}

