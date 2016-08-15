package db.redis;

import java.util.HashMap;

import org.thankjava.toolkit.radom.Sequence;
import org.thankjava.toolkit.thread.ThreadPool;
import org.thankjava.toolkit3d.db.redis.RedisManager;
import org.thankjava.toolkit3d.db.redis.datasource.JedisManager;

public class RedisManagerTest {

	public static void main(String[] args) {
		final RedisManager redis = JedisManager.getInstance();
		
//		System.out.println(redis.lpush("test", new String[]{"aa","cc","bb"}));
		System.out.println(redis.rpop("test"));
//		redis.setStr("str", "测试数据");
		
//		ThreadPool pool = new ThreadPool(50, 100, 600000, 1000000);
//		for (int i = 0; i < 1000000L; i ++) {
//			pool.execute(new Runnable() {
//				@Override
//				public void run() {
//					System.out.println(redis.setStr(Sequence.generateUnique().toString(),Sequence.uuid()[0]));
//				}
//			});
//		}
		
//		System.out.println(redis.isKeyExists("str"));
//		System.out.println(redis.setKeyExpiration("str", 10));
		
//		String[] listValue = new String[]{"aa","bb","cc","dd"};
//		System.out.println(redis.setList("list", listValue));
		
//		HashMap<String, String> hashValue = new HashMap<>();
//		hashValue.put("a", "a");
//		hashValue.put("b", "b");
//		System.out.println(redis.setHash("hash", hashValue));
		
	}
	
}
