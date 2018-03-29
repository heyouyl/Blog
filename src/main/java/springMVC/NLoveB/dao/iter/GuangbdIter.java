package springMVC.NLoveB.dao.iter;

import java.util.List;

/**
 * 
 * @ClassName:  GuangbdIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:03:31   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface GuangbdIter {
	/**
	 * @param <T>
	 * @param guangbdtype 传入手机版本0或电脑版本1
	 */
	<T> List<T> getGuangShowbd(int guangbdtype);
	<T> List<T> getGuangAdminbd();
	<T> T getGuangbd(int guangbdid);
	<T> String saveOrUpdataGuangbd(T[] t);
	/**
	 * 删除广告
	 * @param gbdid
	 * @return
	 */
	String deleteGuangbd(int[] gbdid);
}
