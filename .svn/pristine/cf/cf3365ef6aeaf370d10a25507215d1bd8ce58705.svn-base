package springMVC.NLoveB.dao.iter;

import java.util.List;

import springMVC.NLoveB.po.Comments;

/**
 * 
 * @ClassName:  DaoCommentIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:04:00   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface DaoCommentIter {
	public List<Comments> getComment(int postid);		//获得这个文章的评论
	public Comments getCommentParent(int postid,int nowcommeneid,int parentid);//获得父评论
	
	public Comments saveupdateComments(Comments comment);//添加一条评论
	
	//用户中心的评论展示
	//获得当前用户评论总数
	public <T> List<T> UseridCommentSize(int userid);
	//获得当前用户的评论数据，分页
	public <T> List<T> UseridComment(int userid,int page, int pagesize,int comcount);
	
	/**
	 * 评论的大小
	 * @param iden 标识符 all 全部，1，审核，spam，垃圾 trash回收站
	 * @return
	 */
	<T> List<T> comcount(String iden);		//获得文章的总数据
	/**
	 * 获得符合要求的数据
	 * @param userid
	 * @param page
	 * @param pagesize
	 * @param comcount
	 * @return
	 */
	public <T> List<T> Comments(String iden,int page, int pagesize,int comcount);
	
	/**
	 * 
	 * @param iden 标识符
	 * @param comids	评论id的数组
	 * @return 返回poids 文章id，用以缓存清理
	 */
	String updateoperateComment(String iden, Comments[] comments);
	
	/**
	 * 
	 * @param comids
	 * @return 返回comments数组实体
	 */
	Comments[] getComment(int[] comids);
}
