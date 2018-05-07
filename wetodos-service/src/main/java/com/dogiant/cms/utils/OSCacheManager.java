package com.dogiant.cms.utils;

import java.util.Properties;

import com.opensymphony.oscache.base.AbstractCacheAdministrator;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

public class OSCacheManager {
	private static Properties p = new Properties();
	private static GeneralCacheAdministrator cache = null;

	static {
		p.setProperty(AbstractCacheAdministrator.CACHE_ALGORITHM_KEY,
				"com.opensymphony.oscache.base.algorithm.LRUCache");
		p.setProperty(AbstractCacheAdministrator.CACHE_CAPACITY_KEY, "20000");
		cache = new GeneralCacheAdministrator(p);
	}

	public static Object getObjectFromCache(String key, Object obj, int refreshPeriod) {
		// Cache data key and refresh period
		try {
			// Get from the cache
			obj = (Object) cache.getFromCache(key, refreshPeriod);
		} catch (NeedsRefreshException nre) {
			try {
				// Get the value (probably from the database)
				// obj = getData(sth);
				// Store in the cache
				cache.putInCache(key, obj);
			} catch (Exception ex) {
				// We have the current content if we want fail-over.
				obj = (Object) nre.getCacheContent();
				// It is essential that cancelUpdate is called if the
				// cached content is not rebuilt
				cache.cancelUpdate(key);
				ex.printStackTrace();
			}
		}
		return obj;
	}

	public static void putInCache(String key, Object obj){
		try {
			cache.putInCache(key, obj);
		} catch (Exception e) {
			cache.cancelUpdate(key);
			e.printStackTrace();
		}
	}
}