package springMVC.NLoveB.service.iter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @ClassName:  SerGuangbdIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:09:53   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerGuangbdIter {
	/**
	 * 后台获得数据
	 * @return
	 */
	String getGuangbdJsonAll();
	/**
	 * 根据手机版本0或电脑版本1，返回不同的数据
	 * @param guangbdtype
	 * @return
	 */
	Map<String, Object> getGuangbdShowType(int guangbdtype);
	/**
	 * 更新或者修改广告
	 * @param datajsons
	 * @return
	 */
	String saveupGuangbd(String datajsons);
	/**
	 * 更新或者修改广告
	 * @param guangbdids
	 * @return
	 */
	String delGuangbds(String guangbdids);
	
	/**
	 * 新增或修改幻灯片
	 * @param request
	 * @return
	 */
	String saveSlide(HttpServletRequest request);
	
	/**
	 * 后台，有序的
	 * @return
	 */
	Map<String,Map<String, String>> getLinkedlide();
	/**
	 * 前台，无序的
	 * @return
	 */
	Map<String, Map<String, String>> getIndexslide();
	
}
