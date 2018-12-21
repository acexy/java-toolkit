package com.thankjava.toolkit3d.core.db.redis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 封装常用的redis操作
 * 依赖于 maven[redis.clients:jedis]
 * <p>Function: RedisManager</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年4月18日 上午10:16:37
 */
public interface RedisManager {

    /**
     * set K V
     * <p>Function: set</p>
     * <p>Description: </p>
     *
     * @param key
     * @param value
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月7日 下午5:11:29
     * @version 1.0
     */
    public boolean set(String key, String value);

    /**
     * get K
     * <p>Function: get</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月7日 下午6:11:19
     * @version 1.0
     */
    public String get(String key);

    /**
     * 判断一个key是否存在
     * <p>Function: exists</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月8日 上午10:12:23
     * @version 1.0
     */
    public boolean exists(String key);


    /**
     * 设置一个key的失效时间
     * <p>Function: expire</p>
     * <p>Description: </p>
     *
     * @param key
     * @param expirationTime 单位秒
     * @return 实际操作数据量大于0 则 true
     * @author acexy@thankjava.com
     * @date 2016年3月24日 上午10:58:36
     * @version 1.0
     */
    public boolean expire(String key, int expirationTime);

    /**
     * 设置 set
     * <p>Function: sadd</p>
     * <p>Description: </p>
     *
     * @param key
     * @param setValue
     * @return 实际操作数据量大于0 则 true
     * @author acexy@thankjava.com
     * @date 2016年3月24日 上午11:07:57
     * @version 1.0
     */
    public boolean sadd(String key, String... setValue);

    /**
     * 设置hash
     * <p>Function: hset</p>
     * <p>Description: </p>
     *
     * @param key       redisKey
     * @param hashKey   值hashkey
     * @param hashValue 值
     * @return 实际操作数据量大于0 则 true
     * @author acexy@thankjava.com
     * @date 2016年3月24日 上午11:24:28
     * @version 1.0
     */
    public boolean hset(String key, String hashKey, String hashValue);

    /**
     * 设置多个hash
     *
     * @param key
     * @param hash
     * @return
     */
    public boolean hmset(String key, HashMap<String, String> hash);

    /**
     * 删除key
     * <p>Function: del</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月24日 上午11:27:27
     * @version 1.0
     */
    public boolean del(String key);

    /**
     * lpush list
     * <p>Function: lpush</p>
     * <p>Description: </p>
     *
     * @param key
     * @param listValue
     * @return
     * @author acexy@thankjava.com
     * @date 2016年5月5日 下午3:34:30
     * @version 1.0
     */
    public boolean lpush(String key, String... listValue);

    /**
     * 获取并移除list中尾元素
     * <p>Function: rpop</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年5月5日 下午3:53:48
     * @version 1.0
     */
    public String rpop(String key);


    /**
     * 获取set 数据
     * <p>Function: smembers</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年5月19日 下午2:46:23
     * @version 1.0
     */
    public Set<String> smembers(String key);

    /**
     * 获取hash数据
     * <p>Function: hgetall</p>
     * <p>Description: </p>
     *
     * @param key
     * @return
     * @author acexy@thankjava.com
     * @date 2016年5月19日 下午2:53:03
     * @version 1.0
     */
    public Map<String, String> hgetall(String key);

    public String hget(String key, String field);

    /**
     * hmaget
     * <p>Function: hmget</p>
     * <p>Description: </p>
     *
     * @param key
     * @param fields
     * @return
     * @author acexy@thankjava.com
     * @date 2017年3月29日 下午7:15:05
     * @version 1.0
     */
    public List<String> hmget(String key, String... fields);

    /**
     * 批处理一次性取出一批key值 hgetall
     * <p>Function: hgetallPipelined</p>
     * <p>Description: </p>
     *
     * @param keys
     * @return
     * @author acexy@thankjava.com
     * @date 2017年3月29日 下午6:52:52
     * @version 1.0
     */
    public List<Object> hgetallPipelined(Set<String> keys);

    /**
     * 删除hash
     *
     * @param key
     * @param fields 可选，不填删除整个key
     * @return
     */
    public boolean hdel(String key, String... fields);

    /**
     * 判断提供的数据是否在set里面
     *
     * @param key
     * @param field
     * @return
     */
    public boolean sismember(String key, String field);

    /**
     * 从set中移除
     *
     * @param key
     * @param fields
     */
    public long srem(String key, String... fields);

    /**
     * 原子性递增 1
     * @param key
     */
    public Long incr(String key);
}
