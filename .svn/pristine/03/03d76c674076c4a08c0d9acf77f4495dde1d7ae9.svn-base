package springMVC.NLoveB.dao.iter;

import java.util.List;

/**
 * 
 * @ClassName:  DaoLinksIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:03:55   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface DaoLinksIter {
	/**
	 * 用于前台展示的可见的友情链接
	 * @param visible
	 * @return
	 */
	<T> List<T> allLinks(String visible);
	
	/**
	 * 获得所有的友情链接信息，用于后台管理 需要得到以下子段
	 * url name visible note rating
	 * @return
	 */
	<T> List<T> allLinks();
	
	/**
	 * 修改或新增友情链接
	 * @param t
	 * @return
	 */
	<T> String updateLinks(T[] t);
	
	/**
	 * 删除友情链接
	 * @param linkids
	 * @return
	 */
	String deleteLinks(int[] linkids);
	/**
	 * 查询Links
	 * @param linkid
	 * @return
	 */
	<T> T getLinks(int linkid);
}
