package springMVC.NLoveB.controller.index;

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
import springMVC.NLoveB.service.iter.SerLinksIter;
import springMVC.NLoveB.service.iter.SerLoginOrRegisteriter;
import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  Carpage   
 * @Description:TODO(首页的文章列表分页 )   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:59:47   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller()
@RequestMapping("/page")
public class Carpage {

	@Resource(name = "serloginregister")
	private SerLoginOrRegisteriter serlogingister;
	@Resource(name = "serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="sermenus")
	private MenusIter sermenu;
	@Resource(name="serlinks")
	private SerLinksIter serlinks;

	@RequestMapping("/{page}")
	public ModelAndView articlepage(@PathVariable String page,String s, HttpServletRequest request, HttpServletResponse response) {
		String setting = "arcount=" + configProperties.getProp("arcount") + "&posts_per_page="
				+ configProperties.getProp("posts_per_page") + "&pagination=" + configProperties.getProp("pagination");
		Cookie cookie = new Cookie("setting", setting);
		response.addCookie(cookie);
		ModelAndView model;
		
		if(null==s||s.length()==0){
			model = new ModelAndView("index");
			model.addObject("slnameiden","index");								//当前分类的标识符
			model.addObject("arcount", configProperties.getProp("arcount"));		//数据总数
		}else{
			model = new ModelAndView("search");
			model.addObject("slnameiden","search");								//当前分类的标识符
			model.addObject("searchs",s);								//当前分类的标识符
			model.addObject("arcount", serarposts.getcount(s));		//数据总数
		}
		model.addObject("noticeList", serarposts.getNotice(null==configProperties.setinitToMap("notice_size")?1:Integer.parseInt(configProperties.setinitToMap("notice_size"))));
		
		model.addObject("article", serarposts.getAtticle(page,s));
		model.addObject("tuijie", serarposts.Atticletermsid(serarposts.getSetinitValue("index_tuijie")));
		model.addObject("topmenu",sermenu.guestmenus("topmenu"));	//具体菜单数据
		model.addObject("topnav",sermenu.guestmenus("topnav"));
		model.addObject("latestmenu",sermenu.guestmenus("latestmenu"));
		model.addObject("page",page);		//当前页码
		
		model.addObject("keywords",configProperties.setinitToMap("web_indexkey"));		//首页关键字
		model.addObject("description",configProperties.setinitToMap("web_indexdes"));		//首页的描述
		model.addObject("brand",configProperties.setinitToMap("web_brandtext"));		//首页的品牌描述
		model.addObject("web_custop",configProperties.setinitToMap("web_custop"));		//顶部样式
		model.addObject("web_cusbut",configProperties.setinitToMap("web_cusbut"));		//底部样式
		
		model.addObject("posts_per_page",configProperties.getProp("posts_per_page"));		//每页显示的数据大小
		model.addObject("listlinks", serlinks.allLinks());
		
		return model;
	}
}
