package springMVC.NLoveB.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import springMVC.NLoveB.service.iter.SerCommentIter;
/**
 * 
 * @ClassName:  ConComment   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:01:30   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/user/comment")
public class ConComment {
	@Resource(name="sercomment")
	private SerCommentIter sercomment;
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody String updatecomment(HttpServletRequest request,@RequestHeader(value="User-Agent", defaultValue="") String userAgent,String arid,String commentContent,String author,String email,String userid,String replycommentid){
		
		return sercomment.saveupcomment(Integer.parseInt(arid), commentContent, author, email, Integer.parseInt(userid), userAgent, request.getRemoteAddr(),Integer.parseInt(replycommentid));
	}
}
