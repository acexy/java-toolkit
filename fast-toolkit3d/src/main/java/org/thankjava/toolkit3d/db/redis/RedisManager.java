package org.thankjava.toolkit3d.db.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * 封装常用的redis操作
* 依赖于 maven[redis.clients:jedis]
* <p>Function: RedisManager</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年4月18日 上午10:16:37
* @version 1.0
 */
public interface RedisManager {

	/**
	 * set K V
	* <p>Function: set</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月7日 下午5:11:29
	* @version 1.0
	* @param key
	* @param value
	* @return
	 */
	public boolean set(String key,String value);
	
	/**
	 * get K
	* <p>Function: get</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月7日 下午6:11:19
	* @version 1.0
	* @param key
	* @return
	 */
	public String get(String key);
	
	/**
	 * 判断一个key是否存在
	* <p>Function: exists</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月8日 上午10:12:23
	* @version 1.0
	* @param key
	* @return
	 */
	public boolean exists(String key);
	
	
	/**
	 * 设置一个key的失效时间
	* <p>Function: expire</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月24日 上午10:58:36
	* @version 1.0
	* @param key
	* @param expirationTime 单位秒
	* @return 实际操作数据量大于0 则 true
	 */
	public boolean expire(String key,int expirationTime);
	
	/**
	 * 设置 set
	* <p>Function: sadd</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月24日 上午11:07:57
	* @version 1.0
	* @param key
	* @param setValue
	* @return 实际操作数据量大于0 则 true
	 */
	public boolean sadd(String key,String[] setValue);
	
	/**
	 * 设置hash
	* <p>Function: hset</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月24日 上午11:24:28
	* @version 1.0
	* @param key
	* @param hashValue
	* @return 实际操作数据量大于0 则 true
	 */
	public boolean hset(String key,HashMap<String, String> hashValue);
	
	/**
	 * 删除key
	* <p>Function: del</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月24日 上午11:27:27
	* @version 1.0
	* @param key
	* @return
	 */
	public boolean del(String key);
	
	/**
	 * lpush list
	* <p>Function: lpush</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年5月5日 下午3:34:30
	* @version 1.0
	* @param key
	* @param listValue
	* @return
	 */
	public boolean lpush(String key,String[] listValue);
	
	/**
	 * 获取并移除list中尾元素
	* <p>Function: rpop</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年5月5日 下午3:53:48
	* @version 1.0
	* @param key
	* @return
	 */
	public String rpop(String key);
	
	
	/**
	 * 获取set 数据
	* <p>Function: smembers</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年5月19日 下午2:46:23
	* @version 1.0
	* @param key
	* @return
	 */
	public Set<String> smembers(String key);
	
	/**
	 * 获取hash数据
	* <p>Function: hgetall</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年5月19日 下午2:53:03
	* @version 1.0
	* @param key
	* @return
	 */
	public Map<String, String> hgetall(String key);
}
