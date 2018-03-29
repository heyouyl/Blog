package springMVC.NLoveB.dao.iter;

import java.util.List;

/**
 * 
 * @ClassName:  DaoArticle_pagesIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:04:15   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface DaoArticle_pagesIter {
	<T> List<T> arcount();		//获得page的总数据
	<T> List<T> daogetpages(int page,int pagesize,int arcount);//获得page的分页数据
	
	/**
	 * 获得公告数据
	 * @param ipage
	 * @param ipagesize
	 * @param arcount
	 * @return
	 */
	<T> List<T> daogetnoticess(int ipage, int ipagesize, int arcount);
}
