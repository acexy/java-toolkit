package com.thankjava.toolkit3d.core.db;

import com.thankjava.toolkit.core.reflect.ReflectUtil;
import com.thankjava.toolkit3d.core.db.mongodb.MongoManager;
import com.thankjava.toolkit3d.core.db.mysql.MyBatisManager;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Desc: 负责创建db层的相关数据库实例
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-03
 **/
public final class BasicDBManagerBuilder {

    private BasicDBManagerBuilder() {
    }


    /**
     * 创建RedisManager
     *
     * @param configPath redis配置文件路径, 如果不指定则默认家在 classpath: redis.properties
     * @return
     */
    public static RedisManager buildRedisManager(String... configPath) {
        return (RedisManager) doBuild("com.thankjava.toolkit3d.core.db.redis.impl.RedisManagerImpl", configPath);
    }

    public static RedisManager buildRedisClusterManager(String... configPath) {
        return (RedisManager) doBuild("com.thankjava.toolkit3d.core.db.redis.impl.RedisClusterManagerImpl", configPath);
    }

    /**
     * 创建MongoManager
     *
     * @param configPath redis配置文件路径, 如果不指定则默认家在 classpath: mongodb.properties
     * @return
     */
    public static MongoManager buildMongoManager(String... configPath) {
        return (MongoManager) doBuild("com.thankjava.toolkit3d.core.db.mongodb.impl.MongoManagerImpl", configPath);
    }

    /**
     * 创建MyBatisManager
     * 默认加载classpath: mybatis-config.xml
     *
     * @return
     */
    public static MyBatisManager buildMyBatisManager() {
        return (MyBatisManager) doBuild("com.thankjava.toolkit3d.core.db.mysql.impl.MyBatisManagerImpl", null);
    }

    private static Object doBuild(String classPath, String... configPath) {
        try {
            Method method = ReflectUtil.getMethod(Class.forName(classPath), "init", String.class);
            if (configPath != null && configPath.length > 0) {
                return ReflectUtil.invokeMethod(null, method, configPath[0]);
            } else {
                String str = null;
                return ReflectUtil.invokeMethod(null, method, str);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
