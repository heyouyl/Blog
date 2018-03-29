package springMVC.NLoveB.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import springMVC.NLoveB.service.iter.SerEhcacheIter;
/**
 * 
 * @ClassName:  SerEhcacheImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:41   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serehcache")
public class SerEhcacheImpl implements SerEhcacheIter {
	CacheManager manager = CacheManager.create();	//获得缓存names
	
	@Override
	public String cleanAll() {
		manager.removalAll();
		return "cleanok";
	}

	@Override
	public String cleanName(String name) {
		Cache cache = manager.getCache(name);
		cache.removeAll();
		return "cleanok";
	}

	@Override
	public String cleanKey(String name, String key) {
		Cache cache = manager.getCache(name);
		
		List<?> keys = cache.getKeys();
		for(int i=0;i<keys.size();i++){
			if(keys.get(i).equals(key)){
				cache.remove(key);
				break;
			}
		}
		return "cleanok";
	}
	@Override
	public String cleanKey(String name, String[] key) {
		Cache cache = manager.getCache(name);
		
		for(String k:key){
			cache.remove(k);
		}
		return "cleanok";
	}

	@Override
	public String[] ehcachenames() {
		String[] cacheNames = manager.getCacheNames();
		return cacheNames;
	}

	@Override
	public String ehcachenamekeys(String name) {
		Cache cache = manager.getCache(name);
		List<?> keys = cache.getKeys();
		int Total = keys.size();
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for(Object k:keys){
			map = new HashMap<String,Object>();
			map.put("ehcachename",name);
			map.put("ehcachekey",k);
			list.add(map);
		}
		hashMap.put("Rows", list);
		hashMap.put("Total", Total);
		return JSON.toJSONString(hashMap);
	}

}
