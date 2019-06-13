package com.thankjava.toolkit3d.core.db.redis.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.thankjava.toolkit.core.utils.SourceLoaderUtil;
import com.thankjava.toolkit3d.core.db.redis.RedisManager;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

class RedisManagerImpl implements RedisManager {

    private static JedisPool jedisPool = null;

    private static final String SUCCESS_CODE_STR = "OK";

    private static RedisManager manager = null;

    private static int DEFAULT_DB_INDEX = 0;

    private RedisManagerImpl() {
    }

    private static RedisManager init(String configPath) {
        if (manager == null) {
            manager = new RedisManagerImpl();
        } else {
            return manager;
        }
        Reader reader = null;
        if (configPath == null || configPath.trim().length() == 0) {
            reader = SourceLoaderUtil.getResourceAsReader("redis.properties");
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

            // 创建读jedis池配置实例
            JedisPoolConfig readConfig = new JedisPoolConfig();

            // 设置池配置项值
            readConfig.setMaxTotal(Integer.valueOf(props.getProperty("redis.pool.maxTotal")));
            readConfig.setMaxIdle(Integer.valueOf(props.getProperty("redis.pool.maxIdle")));
            readConfig.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.pool.maxWaitMillis")));
            readConfig.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow")));
            readConfig.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.pool.testOnReturn")));

            String dbIndexStr = props.getProperty("redis.db.index");
            if (dbIndexStr != null && dbIndexStr.trim().length() > 0) {
                int dbIndex = Integer.valueOf(props.getProperty("redis.db.index"));
                if (dbIndex >= 0) {
                    DEFAULT_DB_INDEX = dbIndex;
                }
            }

            String pwd = props.getProperty("redis.pwd");
            if (pwd == null || pwd.trim().length() == 0) {
                //无密码的redis

                jedisPool = new JedisPool(
                        readConfig,
                        props.getProperty("redis.ip"),
                        Integer.valueOf(props.getProperty("redis.port")),
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        null,
                        DEFAULT_DB_INDEX,
                        null);
            } else {

                // 根据配置实例化jedis池
                jedisPool = new JedisPool(
                        readConfig,
                        props.getProperty("redis.ip"),
                        Integer.valueOf(props.getProperty("redis.port")),
                        Integer.valueOf(props.getProperty("redis.timeout")),
                        pwd,
                        DEFAULT_DB_INDEX,
                        null);
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

    private static Jedis getJedis() {
        return jedisPool.getResource();
    }

    private static void returnJedis(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    @Override
    public boolean set(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.set(key, value).equals(SUCCESS_CODE_STR);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnJedis(jedis);
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        if (keys == null || keys.size() == 0) {
            return null;
        }
        Jedis jedis = null;
        Pipeline pipeline = null;
        try {
            jedis = getJedis();
            pipeline = jedis.pipelined();
            for (String key : keys) {
                pipeline.hgetAll(key);
            }
            return pipeline.syncAndReturnAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (pipeline != null) {
                try {
                    pipeline.close();
                } catch (IOException e) {
                }
            }
            returnJedis(jedis);
        }
    }

    @Override
    public boolean hdel(String key, String... fields) {
        if (fields == null || fields.length == 0) {
            return false;
        }
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
        Jedis jedis = null;
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
    public String hget(String key, String field) {
        Jedis jedis = null;
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
