package cache.ehcache;

import org.thankjava.toolkit3d.utils.cache.ehcache.EcacheManager;

public class EcacheManagerTest {

	public static void main(String[] args) {
		EcacheManager manager = new EcacheManager();
		
		manager.setCache("test","aa", "什么");
		System.out.println(manager.getCache("test","aa"));
		manager.shutdown();
	}
}
