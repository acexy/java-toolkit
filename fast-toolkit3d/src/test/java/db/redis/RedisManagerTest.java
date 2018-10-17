package db.redis;

import com.thankjava.toolkit3d.core.db.redis.RedisManager;
import com.thankjava.toolkit3d.core.db.redis.datasource.RedisManagerImpl;

public class RedisManagerTest {

	public static void main(String[] args) {
		final RedisManager redis = RedisManagerImpl.getSingleton();
//		final RedisManager redis = RedisManagerImpl.getSingleton("/Users/acexy/Downloads/redis.properties");

//		System.out.println(redis.lpush("test", new String[]{"aa","cc","bb"}));
//		System.out.println(redis.rpop("test"));
		
//		ThreadPool pool = new ThreadPool(100, 100, 600000, 1000000);
//		for (int i = 0; i < 10000L; i ++) {
//			pool.execute(new Runnable() {
//				@Override
//				public void run() {
					System.out.println(redis.set("str", "测试数据"));
					System.out.println(redis.get("str"));
//				}
//			});
//		}
		
//		System.out.println(redis.isKeyExists("str"));
//		System.out.println(redis.setKeyExpiration("str", 10));
		
//		String[] listValue = new String[]{"aa","bb","cc","dd"};
//		System.out.println(redis.setList("list", listValue));
		
//		HashMap<String, String> hashValue = new HashMap<>();
//		hashValue.putAopConfig("a", "a");
//		hashValue.putAopConfig("b", "b");
//		System.out.println(redis.setHash("hash", hashValue));
		
	}
	
}

