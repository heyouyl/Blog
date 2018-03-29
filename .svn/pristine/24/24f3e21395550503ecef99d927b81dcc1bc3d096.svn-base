package springMVC.NLoveB.dao.iter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:  DaoArticle_postsIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:04:07   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface DaoArticle_postsIter {
	<T> List<T> arcount();		//获得文章的总数据
	<T> List<T> arcount(String s);	//获得搜索关键字的总数据大小
	
	<T> List<T> article(int page,int pagesize,int arcount);		//获得文章数据
	<T> List<T> article(int page,int pagesize,String s,int arcount);		//获得文章数据 搜索
	
	
	<T> List<T> termsid(int termsid);		//获得该分类的最新一篇文章
	<T> List<T> termslug(int page,int pagesize,int arcount,String termslug,String categroy);		//获得该分类(缩略名)的文章 分类显示
	
	<T> T articlid(int arid);		//获得文章数据 详细的
	
	<T, O,P> Map<String,Object> addticle(T t,O[] o,P[] p);
	
	<T> List<T> postmetaviews(int poid);		//给浏览量加1
	<T> void addpostmetaviews(T t);		//给浏览量加1
	<T> List<T> postmeta(int poid);						//根据poid获得postmeta
	
	//用户中心
	// 获得当前用户的当前状态的文章数据大小
	<T> T addUserTicle(T t);
	<T> List<T> userarticleLength(String poststatus, int userid);		//获得文章数据
	// 当前页码	每页大小	当前用户的当前状态的文章数据	文章状态	用户id
	<T> List<T> userarticle(int page,int pagesize,int arcount,String poststatus,int userid);		//获得文章数据
	
	//管理中心
	<T> List<T> adminpost(int page,int pagesize,int arcount,String poststatus);
	<T> void deletearid(T t);		//删除一篇文章
	void updateGuid(int poid);			//添加一篇文章后更新guid的值
	/**
	 * 获得指定大小的公告数据
	 * @param noticesize
	 * @return
	 */
	<T> List<T> article_notice(int noticesize);
}
