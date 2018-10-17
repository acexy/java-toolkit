package db.redis;

import com.thankjava.toolkit3d.core.db.redis.RedisManager;
import com.thankjava.toolkit3d.core.db.redis.datasource.RedisManagerImpl;

public class RedisManagerTest {

    public static void main(String[] args) {
        final RedisManager redis = RedisManagerImpl.getSingleton();
//		final RedisManager redis = RedisManagerImpl.getSingleton("/Users/acexy/Downloads/redis.properties");
        System.out.println(redis.set("str", "测试数据"));
        System.out.println(redis.get("str"));

    }

}

