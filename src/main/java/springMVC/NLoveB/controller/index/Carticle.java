package springMVC.NLoveB.controller.index;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springMVC.NLoveB.service.iter.MenusIter;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerCommentIter;
import springMVC.NLoveB.service.iter.SerLinksIter;
import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  Carticle   
 * @Description:TODO(文章 )   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:00:06   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/archives")
public class Carticle {
	@Resource(name="serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="sermenus")
	private MenusIter sermenu;
	@Resource(name="serlinks")
	private SerLinksIter serlinks;
	@Resource(name="sercomment")
	private SerCommentIter sercomment;
	
	@RequestMapping("/{yyyy}_{MM}_{arid}")
	public ModelAndView category(@PathVariable String yyyy,@PathVariable String MM,@PathVariable String arid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String setting = "arcount="+configProperties.getProp("arcount")+"&posts_per_page="+configProperties.getProp("posts_per_page")+"&pagination="+configProperties.getProp("pagination");
		Cookie cookie = new Cookie("setting",setting);
		response.addCookie(cookie);
		
		ModelAndView model = new ModelAndView("article");
		model.addObject("noticeList", serarposts.getNotice(null==configProperties.setinitToMap("notice_size")?1:Integer.parseInt(configProperties.setinitToMap("notice_size"))));
		
		Map<String, Object> atticleid = serarposts.getAtticleid(arid);
		model.addObject("article", atticleid);
		model.addObject("topmenu",sermenu.guestmenus("topmenu"));	//具体菜单数据
		model.addObject("topnav",sermenu.guestmenus("topnav"));
		model.addObject("slnameiden","articleiden");			//当前分类的标识符
		
		model.addObject("keywords",atticleid.get("keywords"));		//首页关键字
		model.addObject("description",atticleid.get("description"));		//首页的描述
		model.addObject("brand",configProperties.setinitToMap("web_brandtext"));		//首页的品牌描述
		model.addObject("web_custop",configProperties.setinitToMap("web_custop"));		//顶部样式
		model.addObject("web_cusbut",configProperties.setinitToMap("web_cusbut"));		//底部样式
		
		model.addObject("comments", sercomment.postComment(arid));
		
		model.addObject("listlinks", serlinks.allLinks());

		return model;
	}
}
