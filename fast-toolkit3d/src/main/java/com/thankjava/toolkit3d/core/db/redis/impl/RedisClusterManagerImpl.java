package com.thankjava.toolkit3d.core.db.redis.impl;

import com.thankjava.toolkit.core.utils.SourceLoaderUtil;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;
import redis.clients.jedis.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * @Desc: 支持redis cluster 模式
 * @Author: acexy@thankjava.com
 * @Date: 2019-08-14
 **/
class RedisClusterManagerImpl implements RedisManager {

    private static final String SUCCESS_CODE_STR = "OK";

    private static RedisManager manager = null;

    private static JedisCluster jedisCluster;

    private RedisClusterManagerImpl() {

    }

    private static RedisManager init(String configPath) {
        if (manager == null) {
            manager = new RedisClusterManagerImpl();
        } else {
            return manager;
        }
        Reader reader = null;
        if (configPath == null || configPath.trim().length() == 0) {
            reader = SourceLoaderUtil.getResourceAsReader("redis-cluster.properties");
        } else {
            try {
                reader = new FileReader(configPath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            Properties props = new Properties();
            props.load(reader);
            reader.close();
            // 创建读jedis池配置实例
            JedisPoolConfig readConfig = new JedisPoolConfig();

            // 设置池配置项值
            readConfig.setMaxTotal(Integer.valueOf(props.getProperty("redis.pool.maxTotal")));
            readConfig.setMaxIdle(Integer.valueOf(props.getProperty("redis.pool.maxIdle")));
            readConfig.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.pool.maxWaitMillis")));
            readConfig.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow")));
            readConfig.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.pool.testOnReturn")));

            String redisUri = props.getProperty("redis.node.uri");
            if (redisUri == null || redisUri.trim().length() == 0) {
                throw new IllegalArgumentException("redis.node.uri can not be null");
            }
            Set<HostAndPort> nodes = new HashSet<>();
            String[] uris = redisUri.split(";");
            String[] tmp;
            for (String uir : uris) {
                tmp = uir.split(":");
                nodes.add(new HostAndPort(tmp[0], Integer.valueOf(tmp[1])));
            }
            String pwd = props.getProperty("redis.pwd");
            if (pwd != null && pwd.trim().length() > 0) {
                jedisCluster = new JedisCluster(nodes,
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        Integer.valueOf(props.getProperty("redis.maxAttempts")),
                        pwd,
                        readConfig
                );
            } else {
                jedisCluster = new JedisCluster(nodes,
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        Integer.valueOf(props.getProperty("redis.maxAttempts")),
                        readConfig
                );
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return manager;
    }

    private static JedisCluster getJedis() {
        return jedisCluster;
    }

    private static void returnJedis(JedisCluster jedis) {
    }

    @Override
    public boolean set(String key, String value) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return SUCCESS_CODE_STR.equals(jedis.set(key, value));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public String get(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean exists(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.exists(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean expire(String key, int expirationTime) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.expire(key, expirationTime) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean sadd(String key, String... setValue) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.sadd(key, setValue) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean hset(String key, String hashKey, String hashValue) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.hset(key, hashKey, hashValue) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean hmset(String key, HashMap<String, String> hash) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return SUCCESS_CODE_STR.equals(jedis.hmset(key, hash));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean del(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.del(key) > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean lpush(String key, String... listValue) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            long influenceCount = jedis.lpush(key, listValue);
            return influenceCount > 0 ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public String rpop(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.rpop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public Set<String> smembers(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public Map<String, String> hgetall(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        if (fields == null || fields.length == 0) {
            return null;
        }
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.hmget(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public List<Object> hgetallPipelined(Set<String> keys) {
        throw new UnsupportedOperationException("jedis cluster not support this");
    }

    @Override
    public boolean hdel(String key, String... fields) {
        if (fields == null || fields.length == 0) {
            return false;
        }
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.hdel(key, fields) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public boolean sismember(String key, String field) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.sismember(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public long srem(String key, String... fields) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.srem(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public Long incr(String key) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.incr(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public void close() {
        if (jedisCluster != null) {
            jedisCluster.close();
        }
    }

    @Override
    public String hget(String key, String field) {
        JedisCluster jedis = null;
        try {
            jedis = getJedis();
            return jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            returnJedis(jedis);
        }
    }
}
