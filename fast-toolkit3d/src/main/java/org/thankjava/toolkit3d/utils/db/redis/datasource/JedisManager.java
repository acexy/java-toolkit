package org.thankjava.toolkit3d.utils.db.redis.datasource;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.thankjava.toolkit.resource.SourceLoader;
import org.thankjava.toolkit3d.utils.db.redis.RedisManager;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisManager implements RedisManager{

	private static JedisPool jedisPool = null;
	
	private static final String SUCCESS_CODE_STR = "OK";
	
	private static JedisManager manager = null;
	
	
	public static JedisManager getInstance(){
		if(manager == null){
			manager = new JedisManager();
		}
		return manager;
	}
	
	private JedisManager(){
		init();
	}
	
	private void init() {
		
		Reader reader = null;
		try {
			Properties props = new Properties();
			reader = SourceLoader.getResourceAsReader("redis.properties");
			props.load(reader);

			// 创建读jedis池配置实例
			JedisPoolConfig readConfig = new JedisPoolConfig();

			// 设置池配置项值
			readConfig.setMaxTotal(Integer.valueOf(props.getProperty("redis.pool.maxTotal")));
			readConfig.setMaxIdle(Integer.valueOf(props.getProperty("redis.pool.maxIdle")));
			readConfig.setMaxWaitMillis(Long.valueOf(props.getProperty("redis.pool.maxWaitMillis")));
			readConfig.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.pool.testOnBorrow")));
			readConfig.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.pool.testOnReturn")));

			String pwd = props.getProperty("redis.pwd");
			if(pwd == null || pwd.trim().length() == 0){
				jedisPool = new JedisPool(
						readConfig,
						props.getProperty("redis.ip"),
						Integer.valueOf(props.getProperty("redis.port")),
						Integer.valueOf(props.getProperty("redis.timeout")));
			}else{
				// 根据配置实例化jedis池
				jedisPool = new JedisPool(
						readConfig,
						props.getProperty("redis.ip"),
						Integer.valueOf(props.getProperty("redis.port")),
						Integer.valueOf(props.getProperty("redis.timeout")),
						pwd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader != null){
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	static void returnJedis(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		} 
	}

	@Override
	public boolean set(String key, String value) {
		Jedis jedis = getJedis();
		boolean flag = jedis.set(key, value) .equals(SUCCESS_CODE_STR);
		returnJedis(jedis);
		return flag;
	}

	@Override
	public String get(String key) {
		Jedis jedis = getJedis();
		String str = jedis.get(key);
		returnJedis(jedis);
		return str;
	}

	@Override
	public boolean exists(String key) {
		Jedis jedis = getJedis();
		boolean flag = jedis.exists(key);
		returnJedis(jedis);
		return flag;
	}

	@Override
	public boolean expire(String key, int expirationTime) {
		Jedis jedis = getJedis();
		boolean flag = jedis.expire(key, expirationTime) > 0 ? true : false;
		returnJedis(jedis);
		return flag;
	}

	@Override
	public boolean sadd(String key, String[] setValue) {
		if(setValue == null || setValue.length == 0){
			return false;
		}
		Jedis jedis = getJedis();
		boolean flag = jedis.sadd(key, setValue) > 0 ? true : false;
		returnJedis(jedis);
		return flag;
	}

	@Override
	public boolean hset(String key,HashMap<String, String> hashValue) {
		if(hashValue == null || hashValue.size() == 0){
			return false;
		}
		Jedis jedis = getJedis();
		long influenceCount = 0;
		for (Map.Entry<String, String> entry : hashValue.entrySet()) {
			influenceCount += jedis.hset(key, entry.getKey(), entry.getValue());
		}
		returnJedis(jedis);
		boolean flag = influenceCount > 0 ? true : false;
		return flag;
	}

	@Override
	public boolean del(String key) {
		Jedis jedis = getJedis();
		boolean flag = jedis.del(key) > 0 ? true : false;
		returnJedis(jedis);
		return flag;
	}

	@Override
	public boolean lpush(String key, String[] listValue) {
		Jedis jedis = getJedis();
		long influenceCount = jedis.lpush(key, listValue);
		returnJedis(jedis);
		boolean flag = influenceCount > 0 ? true : false;
		return flag;
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = getJedis();
		String str = jedis.rpop(key);
		returnJedis(jedis);
		return str;
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = getJedis();
		Set<String> set = jedis.smembers(key);
		returnJedis(jedis);
		return set;
	}
	
	@Override
	public Map<String, String> hgetall(String key) {
		Jedis jedis = getJedis();
		Map<String, String> map = jedis.hgetAll(key);
		returnJedis(jedis);
		return map;
	}
}
