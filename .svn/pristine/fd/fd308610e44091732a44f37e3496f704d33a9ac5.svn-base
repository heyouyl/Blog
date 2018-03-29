package springMVC.NLoveB.service.iter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import springMVC.NLoveB.po.Term_taxonomy;

/**
 * 
 * @ClassName:  SerArticle_PostsIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:09:16   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerArticle_PostsIter {
	int getcount();		//获得文章的总数据
	int getcount(String s);	//获得关键字的数据大小
	
	public List<Map<String,Object>> getAtticle(String page,String s);		//获得所有文章数据,分页数据
	public Map<String,Object> getAtticleid(String arid);		//获得文章数据,详细的
	int catetagsize(String termsname,String categroy);
	public List<Map<String,Object>> getArtermsslug(String page,String termsname,String categroy);		//获得分类（缩略名）文章数据,分页数据
	public Map<String,String> Atticletermsid(String termsid);		//获得文章数据,分页数据
	public String getSetinitValue(String key);						//获得setinit里面某个key
	//通过categoryid获得文章的id，用以清除缓存
	public int[] categoryidToObject(int categoryid);
	
	//浏览一次文章加1
	public <T> T postmetaview(int poid);
	public <T> void addpostmetaview(T t);
	
	/*
	 * 用户中心我的文章，各种状态的文章数据展示 返回如下格式
	 * {
	 * "items":
	 * 	[
	 * 		{"thumb":"http:\/\/www.028888.net\/wp-content\/themes\/dux\/img\/thumbnail.png","link":"http:\/\/www.028888.net\/?p=1694","title":"\u5e7f\u53d1\u534e\u798f","desc":"\u54c8\u54c8\u54c8\u54c8\u54c8\u54c8\u54c8\u54c8\u54c8\u54c8 \u3010\u535a\u4e3b\u63a8\u4ecb\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u652f\u6301\u535a\u4e3b\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u6253\u8d4f\u535a\u4e3b\u3011 \u5982\u679c\u89c9\u5f97\u535a\u6587\u5bf9\u60a8\u6709\u5e2e\u52a9\uff0c\u60a8\u53ef\u4ee5\u6253\u8d4f\u535a\u4e3b\u54e6...","time":"2016-12-22 15:50","cat":"java\u57fa\u7840","view":0,"comment":0,"like":0},
	 * 		{"thumb":"http:\/\/www.028888.net\/wp-content\/themes\/dux\/img\/thumbnail.png","link":"http:\/\/www.028888.net\/?p=1004","title":"CentOS7\u4e0b\u4f7f\u7528hadoop2.7.2\u521b\u5efa\u4e3a\u5206\u662f","desc":"http:\/\/jingpin.jikexueyuan.com\/article\/20038.html http:\/\/www.linuxidc.com\/Linux\/2014-10\/108448.htm http:\/\/baike.xsoftlab...","time":"2016-05-14 8:22","cat":"java\u57fa\u7840","view":0,"comment":0,"like":0},
	 * 		{"thumb":"http:\/\/www.028888.net\/wp-content\/themes\/dux\/img\/thumbnail.png","link":"http:\/\/www.028888.net\/?p=720","title":"OneToOne \u4e3b\u952e\u53c8\u662f\u5916\u952e\u7684\u914d\u7f6e\u65b9\u5f0f","desc":"\u3010\u535a\u4e3b\u63a8\u4ecb\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u652f\u6301\u535a\u4e3b\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u6253\u8d4f\u535a\u4e3b\u3011 \u5982\u679c\u89c9\u5f97\u535a\u6587\u5bf9\u60a8\u6709\u5e2e\u52a9\uff0c\u60a8\u53ef\u4ee5\u6253\u8d4f\u535a\u4e3b\u54e6\u672c\u535a\u5ba2\u51e0\u4e4e\u5168\u4e3a\u539f\u521b\uff01\u8f6c...","time":"2016-01-12 10:34","cat":"java\u57fa\u7840","view":0,"comment":0,"like":0},
	 * 		{"thumb":"http:\/\/www.028888.net\/wp-content\/themes\/dux\/img\/thumbnail.png","link":"http:\/\/www.028888.net\/?p=671","title":"","desc":"\u3010\u535a\u4e3b\u63a8\u4ecb\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u652f\u6301\u535a\u4e3b\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u6253\u8d4f\u535a\u4e3b\u3011 \u5982\u679c\u89c9\u5f97\u535a\u6587\u5bf9\u60a8\u6709\u5e2e\u52a9\uff0c\u60a8\u53ef\u4ee5\u6253\u8d4f\u535a\u4e3b\u54e6\u672c\u535a\u5ba2\u51e0\u4e4e\u5168\u4e3a\u539f\u521b\uff01\u8f6c...","time":"2015-12-13 15:31","cat":"java\u57fa\u7840","view":0,"comment":0,"like":0},
	 * 		{"thumb":"http:\/\/www.028888.net\/wp-content\/themes\/dux\/img\/thumbnail.png","link":"http:\/\/www.028888.net\/?p=669","title":"","desc":"&nbsp;&nbsp; \u3010\u535a\u4e3b\u63a8\u4ecb\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u652f\u6301\u535a\u4e3b\u3011SpringMVC Hibernate \u5f00\u53d1\u7684\u4e00\u5957\u5546\u57ce\u7cfb\u7edf\u3010\u4ec5\u4f9b\u5b66\u4e60\u3011\u3010\u6253\u8d4f\u535a\u4e3b\u3011 \u5982\u679c\u89c9\u5f97\u535a\u6587\u5bf9\u60a8\u6709\u5e2e\u52a9\uff0c\u60a8\u53ef\u4ee5\u6253\u8d4f\u535a...","time":"2015-12-13 15:26","cat":"java\u57fa\u7840","view":1,"comment":0,"like":0}
	 * 	],
	 * "max":5
	 * }
	 */
	
	//用户中心
	public String useractionjson(int page,String poststatus, int userid);
	//获得当前用户的该分类文章的数据大小
	public int useractionstatuslen(String poststatus, int userid);
	/*
	 * future 	添加一篇定时文章
	 * pending 	添加一篇审核文章
	 * draft 	添加一篇草稿文章
	 * Trash	添加回收站文章
	 */
	String addUserArticle(String arname,String arcontent,String link,String userid,String poststatus);		//添加一篇草稿文章
	void updateArticleguid(int poid);		//添加一篇文章后给guid更新值
	
	//管理中心
	//获得所有文章状态的文章
	String adminallpostsjson(String page,String pagesize,String poststatus);
	String delarid(String poid);
	String updateArticleFast(HttpServletRequest request);		//快速修改一篇文章
	Map<String,Object> getarticleMap(String arid);		//快速修改一篇文章
	void subtermsone(Term_taxonomy[] oldtt);		//将原始的terms减
	
	Map<String,Object> updateArticle(HttpServletRequest request,String poid);			//添加一篇文章
	String updateeditArticle(HttpServletRequest request);

	/**
	 * 修改或添加一篇页面
	 * @param request
	 * @param poid
	 * @return
	 */
	String updatePage(HttpServletRequest request,String poid);			//添加一篇文章
	String updateeditPage(HttpServletRequest request);
	
	/**
	 * 编辑公告
	 * @param request
	 */
	String updateeditnotice(HttpServletRequest request);
	/**
	 * 添加公告
	 * @param request
	 * @param parameter
	 */
	String updatenotice(HttpServletRequest request, String parameter);
	/**
	 * 获得公告数据
	 * @param setinitToMap
	 * @return
	 */
	List<Map<String,Object>> getNotice(int noticesize);
}