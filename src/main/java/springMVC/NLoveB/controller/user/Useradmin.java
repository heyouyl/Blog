package springMVC.NLoveB.controller.user;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springMVC.NLoveB.service.iter.MenusIter;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerCommentIter;
import springMVC.NLoveB.service.iter.SerLinksIter;
import springMVC.NLoveB.service.iter.SerUserInfoIter;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  Useradmin   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:01:17   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/user/admin")
public class Useradmin {
	@Resource(name="sermenus")
	private MenusIter sermenu;
	@Resource(name="serlinks")
	private SerLinksIter serlinks;
	@Resource(name="serarposts")
	private SerArticle_PostsIter serpost;
	@Resource(name="sercomment")
	private SerCommentIter sercomment;
	@Resource(name="seruserinfo")
	private SerUserInfoIter seruserinfo;
	//用户管理
	@RequestMapping("/index")
	public ModelAndView lindex(HttpSession session,String useriden){
		ModelAndView model = new ModelAndView("user/user");
		//获得userid
		int userid = 0;
		Map<String,Object> map = (Map<String, Object>) session.getAttribute("userlog");
		if(null==map){
			System.out.println("空空 空");
		}else{
			userid = (int) map.get("userid");
		}
		if(null==useriden||useriden.length()==0){
			useriden = "comments";
		}
		model.addObject("categoryiden", "useradmin");
		if(useriden.equals("all")){
			//获得当前用户的文章状态的数据大小
			model.addObject("allsize",serpost.useractionstatuslen("all", userid));
			model.addObject("publishsize",serpost.useractionstatuslen("publish", userid));
			model.addObject("futuresize",serpost.useractionstatuslen("future", userid));
			model.addObject("pendingsize",serpost.useractionstatuslen("pending", userid));
			model.addObject("draftsize",serpost.useractionstatuslen("draft", userid));
			model.addObject("trashsize",serpost.useractionstatuslen("trash", userid));
		}
		if(useriden.equals("comments")){
			//评论首页的分页显示
			model.addObject("arcount", sercomment.UserCommentLength(userid));		//评论总数
			model.addObject("page",1);		//当前页码
			model.addObject("posts_per_page",configProperties.getProp("posts_per_page"));		//每页显示的数据大小
			model.addObject("usercomment",sercomment.modelcomment(String.valueOf(userid), "1").get("items"));		//评论数据
		}
		model.addObject("useriden",useriden);
		
		model.addObject("slnameiden","useradmin");
		
		model.addObject("topmenu",sermenu.guestmenus("topmenu"));	//具体菜单数据
		model.addObject("topnav",sermenu.guestmenus("topnav"));
		model.addObject("listlinks", serlinks.allLinks());
		return model;	
	}
	//获得当前状态的用户文章
	@RequestMapping(value="/posts/all", produces="application/json;charset=UTF-8")
	public @ResponseBody String userAction(int paged,String status, int userid){
		
		return serpost.useractionjson(paged, status, userid);
	}	
	//新增一篇文章
	@RequestMapping("/update/{poststatus}")
	public @ResponseBody String updatenew(@PathVariable String poststatus,HttpServletRequest request){
		if(poststatus.equals("info")){
			return seruserinfo.opuserinfo(request.getParameter("userid"), request.getParameter("displayname"),request.getParameter("blogurl"), request.getParameter("user_qq"), request.getParameter("user_weixin"), request.getParameter("user_weibo"));
		}else if(poststatus.equals("password")){
			return seruserinfo.opuserpass(request.getParameter("userid"), request.getParameter("passwordold"), request.getParameter("password"));
		}else if(poststatus.equals("lostpass")){
			//return uppassok
			return seruserinfo.opuserpass(request.getParameter("oldpass"),request.getParameter("emailkey"));
		}else{
			return serpost.addUserArticle(request.getParameter("post_title"), request.getParameter("post_content"), request.getParameter("post_url"), request.getParameter("userid"), poststatus);
		}
	}

}
