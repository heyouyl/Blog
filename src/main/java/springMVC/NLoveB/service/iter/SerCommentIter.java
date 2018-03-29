package springMVC.NLoveB.service.iter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName:  SerCommentIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:09:09   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerCommentIter {
	public List<Map<String,Object>> postComment(String postid);
	public String saveupcomment(int arid,String content,String author,String email,int userid,String useragent,String ip,int replycommentid);

	/*
	 * 获得用户评论 JSON
	 * {
	 *		"items":[
	 *			{"content":"\u6709\u4ec0\u4e48\u95ee\u9898\u5417","post_link":"http:\/\/www.028888.net\/archives\/2016_01_716.html#comment-884","post_title":"\u4e00\u4e2a\u5947\u602a\u7684\u65f6\u95f4\u683c\u5f0f\uff1a2016-01-02T16:00:00.000Z","time":"2016-12-30 17:51:29"},
	 *			{"content":"bug\u89e3\u51b3\u4e86\u5c31\u597d\u3002\u611f\u8c22\u56de\u590d\uff01","post_link":"http:\/\/www.028888.net\/archives\/2016_08_1489.html#comment-684","post_title":"\u5982\u4f55\u5728eclipse\u4e2d\u4f7f\u7528\u4e24\u4e2ajdk \u540c\u65f6\u652f\u6301jdk8\u548cjdk7","time":"2016-08-18 01:47:04"},
	 *			{"content":"\u521a\u521b\u5efa\u597d\u7684maven\u9879\u76ee\uff0cjsp\u5934\u90e8\u62a5\u9519  \u662f\u56e0\u4e3a\u4f60\u6ca1\u6709\u6dfb\u52a0\u76f8\u5173\u4f9d\u8d56jar\u5305\r\npom.xml\u91cc\u9762\u6dfb\u52a0\u5982\u4e0b\u4ee3\u7801\u5373\u53ef\u89e3\u51b3\r\n\r\n\tjavax\r\n\tjavaee-api\r\n\t6.0\r\n\tprovided\r\n","post_link":"http:\/\/www.028888.net\/archives\/2016_08_1445.html#comment-502","post_title":"\u521a\u521b\u5efa\u597d\u7684maven\u9879\u76ee\uff0c\u4e3a\u4f55jsp\u5934\u90e8\u62a5\u9519","time":"2016-08-04 22:38:03"},
	 *		"max":3
	 * }
	 */

	//获得当前用户的所有评论总数
	public int UserCommentLength(String userid);
	public int UserCommentLength(int userid);
	//获得前端需要的数据
	public Map<String,Object> modelcomment(String userid,String page);
	
	/**
	 * 获得所有的评论
	 * @param page   页码
	 * @param pagesize  每页数据大小
	 * @param iden 	标识，all 1 spam trash
	 * @return
	 */
	String comAll(String page,String pagesize,String iden);
	
	/**
	 * 更新评论的状态 
	 * @param iden	1通过 0标记为垃圾 -1放进回收站 -2删除
	 * @param comids
	 * @return poid 逗号隔开
	 */
	String upStatusComment(String iden,String comids);
}
