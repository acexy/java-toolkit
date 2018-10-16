package cache.ehcache;

import com.thankjava.toolkit3d.core.ehcache.EhcacheManager;

public class EcacheManagerTest {

	public static void main(String[] args) {
		EhcacheManager manager = new EhcacheManager();
		
		manager.setCache("test","aa", "什么");
		System.out.println(manager.getCache("test","aa"));
		manager.shutdown();
	}
}
