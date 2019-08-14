package db.redis;

import com.thankjava.toolkit3d.core.db.BasicDBManagerBuilder;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;

/**
 * @Desc: TODO
 * @Author: acexy@thankjava.com
 * @Date: 2019-08-14
 **/
public class RedisClusterManagerTest {
    public static void main(String[] args) {
        RedisManager redis = BasicDBManagerBuilder.buildRedisClusterManager("/Users/acexy/Development/config/acexy/redis-cluster.properties");
        System.out.println(redis.set("test", "123456"));
        System.out.println(redis.get("test"));

    }
}
