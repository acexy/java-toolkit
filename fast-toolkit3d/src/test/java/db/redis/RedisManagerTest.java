package db.redis;

import com.thankjava.toolkit3d.core.db.BasicDBManagerBuilder;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;

public class RedisManagerTest {

    public static void main(String[] args) {
        final RedisManager redis = BasicDBManagerBuilder.buildRedisManager("/Users/acexy/Development/config/acexy/redis.properties");
        redis.set("redis", "redis");
        System.out.println(redis.get("redis"));
    }

}

