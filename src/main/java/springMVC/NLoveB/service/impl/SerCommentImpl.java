package springMVC.NLoveB.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import springMVC.NLoveB.dao.iter.DaoArticle_postsIter;
import springMVC.NLoveB.dao.iter.DaoCommentIter;
import springMVC.NLoveB.dao.iter.DaoUserPermissionsIter;
import springMVC.NLoveB.po.Comments;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.po.Users;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerCommentIter;
import springMVC.NLoveB.service.iter.SerEhcacheIter;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  SerCommentImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:46   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("sercomment")
public class SerCommentImpl implements SerCommentIter {
	@Resource(name="daocomment")
	private DaoCommentIter dcomment;
	@Resource(name="serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="daoarposts")
	private DaoArticle_postsIter darpost;
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	@Resource(name="daouserper")
	private DaoUserPermissionsIter daouserper;
	
	@Override
	public List<Map<String, Object>> postComment(String postid) {
		int pid = Integer.parseInt(postid);
		List<Comments> comments = new ArrayList<Comments>();
		comments = dcomment.getComment(pid);
		//创建前端所需要的list<map>
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int len=comments.size();
		for(int i=0;i<len;i++){
			Comments comment = comments.get(i);
			Map<String, Object> map = new HashMap<String, Object>();
			/*
			 * 需要获得字段信息
			 * comment_ID：自增唯一ID
			 * comment_post_ID：对应文章ID  可不用获得
			 * comment_author：评论者 
			 * comment_author_email：评论者邮箱 
			 * comment_author_url：评论者网址 
			 * comment_author_IP：评论者IP 
			 * comment_date：评论时间 
			 * comment_content：评论正文 
			 * comment_parent：父评论ID 
			 * user_id：评论者用户ID（不一定存在）
			 */
			int commentid = comment.getCommentid();
			map.put("commentid", commentid);
			map.put("commentauthor", comment.getCommentauthor());
			map.put("commentauthoremail", comment.getCommenetauthoremail());
			map.put("commentauthorurl", comment.getCommentauthorurl());
			map.put("commentauthorip", comment.getCommentauthorip());
			
			Date commenetdate = comment.getCommenetdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(commenetdate);
			map.put("commentdate", format);
			map.put("commentcontent", comment.getCommenetcontent());
			map.put("commentuserid", comment.getCommenetuserid());
			
			int commenetparent = comment.getCommenetparent();
			if(commenetparent>0){
				Comments commentParent = dcomment.getCommentParent(pid, commentid, commenetparent);
				
				map.put("pcommentparentauthor", commentParent.getCommentauthor());		//父评论者
				map.put("pcommentparentuid", commentParent.getCommenetuserid());		//子评论的父评论id
				String contenthtml = commentParent.getCommenetcontent().replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<[^>]*>", "\n\t");
				int plen = contenthtml.length();
				map.put("pcommentcontent", contenthtml.substring(0, plen>=15?15:len));//截取父评论
			}
			list.add(map);
		}
		
		return list;
	}

	@Override
	public String saveupcomment(int arid, String content, String author, String email, int userid, String useragent,String ip,int replycommentid) {
		Comments comment = new Comments();
		
		comment.setCommentauthorip(ip);
		comment.setCommentpostid(arid);
		comment.setCommenetcontent(content);
		comment.setCommentauthor(author);
		comment.setCommenetauthoremail(email);
		comment.setCommenetuserid(userid);
		comment.setCommenetagent(useragent);
		comment.setCommentauthorip(ip);
		comment.setCommenetdate(new Date());
		comment.setCommenetparent(replycommentid);//回复给谁？
		//实体默认值有问题，设置的默认值是0，但是数据库没有更新为0，还是1，因此这里设置
		if(!author.equals("java")){
			comment.setCommenetapproved("0");
		}
		
		
		comment.setCommenettype("");
		Comments saveupdateComments = dcomment.saveupdateComments(comment);
		if(saveupdateComments.getCommentid()<=0){
			return "error";
		}else{
			//评论完后清楚对应的缓存
			serehcache.cleanKey("commentsCache","getComment"+arid);		//清除Name里面的key缓存
			return "save";
		}
	}

	@Override
	public int UserCommentLength(String userid) {
		List<Number> arcount = dcomment.UseridCommentSize(Integer.parseInt(userid));
		return arcount.get(0).intValue();
	}

	@Override
	public int UserCommentLength(int userid) {
		List<Number> arcount = dcomment.UseridCommentSize(userid);
		return arcount.get(0).intValue();
	}
	
	@Override
	public Map<String, Object> modelcomment(String userid,String page) {
		//获得总大小
		int len = UserCommentLength(userid);
		Map<String,Object> hashMap = new HashMap<String,Object>();
		Map<String,String> map = null;
		List<Map<String,String>> listmap = new ArrayList<Map<String,String>>();
		//获得comment数据
		List<Comments> useridComment = dcomment.UseridComment(Integer.parseInt(userid),Integer.parseInt(page), Integer.parseInt(configProperties.getProp("posts_per_page")), len);
		for(Comments ucomment:useridComment){
			map = new HashMap<String,String>();
			Date commenetdate = ucomment.getCommenetdate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String format = sdf.format(commenetdate);
			map.put("commentdate", format);
			map.put("commentcontent", ucomment.getCommenetcontent());
			map.put("commentid", String.valueOf(ucomment.getCommentid()));
			if(ucomment.getCommenetapproved().equals("1")){
				map.put("commentiden", "通过");
			}else if(ucomment.getCommenetapproved().equals("0")){
				map.put("commentiden", "审核");
			}else{
				map.put("commentiden", "未知");
			}
			Posts post = darpost.articlid(ucomment.getCommentpostid());
			map.put("posttitle", post.getPotitle());
			map.put("posturl", post.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+post.getPoid()+".html");
			listmap.add(map);
		}
		hashMap.put("items", listmap);
		hashMap.put("max", len);
		return hashMap;
	}

	@Override
	public String comAll(String page, String pagesize, String iden) {
		if(null==page||page.length()==0){
			page="1";
		}
		if(null==pagesize||pagesize.length()==0){
			pagesize="10";
		}
		//获得对应状态的评论信息
		List<Number> comcountlist = dcomment.comcount(iden);
		int comcount = comcountlist.get(0).intValue();
		//获得对应状态的数据
		List<Comments> commentList = dcomment.Comments(iden, Integer.parseInt(page), Integer.parseInt(pagesize), comcount);
		
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		for(Comments com:commentList){
			map = new HashMap<String,Object>();
			map.put("comid", com.getCommentid());//评论id
			map.put("compostid",com.getCommentpostid());		//文章id
			map.put("author", com.getCommentauthor());		//评论者
			map.put("comip", com.getCommentauthorip());	//评论IP
			map.put("comstatus", com.getCommenetapproved());			//评论状态
			map.put("comdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(com.getCommenetdate()));//评论时间
			map.put("comcontent", com.getCommenetcontent());	//评论内容
			//map.put("arurl", p.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+pid+".html");
			//通过文章id得到文章的时间
			Map<String, Object> atticleid = serarposts.getAtticleid(String.valueOf(com.getCommentpostid()));
			map.put("arname", atticleid.get("arname"));
			map.put("arurl", atticleid.get("arurl"));
			//通过父评论者得到父评论者的名称
			if(com.getCommenetuserid()==0){
				map.put("comParentUser", "");
			}else{
				map.put("comParentUser",((Users) daouserper.useridinfo(com.getCommenetuserid())).getUsername());
			}
			
			list.add(map);
		}
		hashMap.put("Rows",list);
		hashMap.put("Total", comcount);
		return JSON.toJSONString(hashMap);
	}

	/*
	 * (non-Javadoc)1通过 0带审核 -1标记为垃圾 -2放进回收站 -3删除  返回的是一个poids，需要对缓存进行处理。否则不生效
	 * @see springMVC.NLoveB.service.iter.SerCommentIter#upStatusComment(java.lang.String, java.lang.String)
	 */
	@Override
	public String upStatusComment(String iden, String comids) {
		int i = comids.split(",").length;
		int[] k = new int[i];
		for(int j=0;j<i;j++){
			k[j] = Integer.parseInt(comids.split(",")[j]);
		}
		return dcomment.updateoperateComment(iden, dcomment.getComment(k));

	}

}
