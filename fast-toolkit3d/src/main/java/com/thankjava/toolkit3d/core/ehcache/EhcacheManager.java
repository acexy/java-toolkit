package com.thankjava.toolkit3d.core.ehcache;

import com.thankjava.toolkit.core.utils.ClassSourceLoaderUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheManager {

    private static CacheManager cacheManager = null;

    public EhcacheManager() {
        cacheManager = CacheManager.create(ClassSourceLoaderUtil.getResourceAsInputStream("ehcache.xml"));
    }

    public EhcacheManager(String configFilePath) {
        CacheManager.create(configFilePath);
    }

    /**
     * 设置缓存
     * <p>Function: setCache</p>
     * <p>Description: </p>
     *
     * @param cacheName 缓存名 ehcache.xml里面配置的缓存策略
     * @param cacheKey  缓存key
     * @param object
     * @author acexy@thankjava.com
     * @date 2016年4月18日 下午5:41:37
     * @version 1.0
     */
    public void setCache(String cacheName, String cacheKey, Object object) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return;
        }
        cache.put(new Element(cacheKey, object));
    }

    public Object getCache(String cacheName, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return null;
        }
        Element element = cache.get(cacheKey);
        if (element == null) {
            return null;
        }
        return element.getObjectValue();
    }

    public void remove(String cacheName, String cacheKey) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null) {
            return;
        }

        cache.remove(cacheKey);
    }

    public void shutdown() {
        cacheManager.shutdown();
    }
}
