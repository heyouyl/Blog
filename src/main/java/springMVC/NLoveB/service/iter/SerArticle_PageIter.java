package springMVC.NLoveB.service.iter;

import java.util.Map;
/**
 * 
 * @ClassName:  SerArticle_PageIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:10:03   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerArticle_PageIter {
	//管理中心
	//获得所有文章状态的文章
	String adminpgesjson(String page,String pagesize);
	Map<String, Object> getPageid(String pageid);
	int getCount();
	/**
	 * 获得所有的公告
	 * @param page
	 * @param pagesize
	 * @return
	 */
	String adminnoticejson(String page, String pagesize);
}