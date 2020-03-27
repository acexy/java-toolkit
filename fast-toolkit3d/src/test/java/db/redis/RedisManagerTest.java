package db.redis;

import com.thankjava.toolkit.core.thread.ThreadPool;
import com.thankjava.toolkit.core.thread.ThreadUtil;
import com.thankjava.toolkit3d.core.db.BasicDBManagerBuilder;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;

import java.util.UUID;

public class RedisManagerTest {

    public static void main(String[] args) throws InterruptedException {
        final RedisManager redis = BasicDBManagerBuilder.buildRedisManager();

        final ThreadPool threadPool = new ThreadPool();
        for (int idx = 0; idx < 10; idx++) {
            for (int i = 0; i <= 10; i++) {
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        redis.set(UUID.randomUUID().toString(), UUID.randomUUID().toString());
                    }
                });
            }
            Thread.sleep(100);
        }

        ThreadUtil.runWhenJVMExit(new Runnable() {
            @Override
            public void run() {
                redis.close();
                threadPool.destroy();
            }
        });
    }

}

