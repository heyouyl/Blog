package springMVC.NLoveB.service.iter;
/**
 * 
 * @ClassName:  SerEhcacheIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:09:58   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerEhcacheIter {
	String cleanAll();		//清除所有的缓存
	String cleanName(String name);		//清除所有的Name缓存
	String cleanKey(String name,String key);		//清除Name里面的key缓存
	String cleanKey(String name, String[] key);
	
	String[] ehcachenames();			//获得缓存的name
	String ehcachenamekeys(String name);		//获得缓存的name的keys
}
