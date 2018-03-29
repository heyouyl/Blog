package springMVC.NLoveB.utils;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
/**
 * 
 * @ClassName:  EhcacheUtil   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:16:50   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class EhcacheUtil {

	public static void ehcache(){
		CacheManager manager = CacheManager.create();
		String[] cacheNames = manager.getCacheNames();
		Cache cache = manager.getCache(cacheNames[0]);		//获得缓存的name 如 baseCache
		List<?> keys = cache.getKeys();
		for(int i=0;i<keys.size();i++){
			System.out.println(keys.get(i));
		}
	}
	public static void cleanAll(){
		CacheManager manager = CacheManager.create();
		manager.clearAll();
	}
}
