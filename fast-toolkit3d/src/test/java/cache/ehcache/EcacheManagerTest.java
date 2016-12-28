package cache.ehcache;

import com.thankjava.toolkit3d.cache.ehcache.EcacheManager;

public class EcacheManagerTest {

	public static void main(String[] args) {
		EcacheManager manager = new EcacheManager();
		
		manager.setCache("test","aa", "什么");
		System.out.println(manager.getCache("test","aa"));
		manager.shutdown();
	}
}
