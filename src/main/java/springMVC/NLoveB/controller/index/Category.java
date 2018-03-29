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
import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  Category   
 * @Description:TODO( 分类 )   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:00:50   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/fl")
public class Category {
	@Resource(name="serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="sermenus")
	private MenusIter sermenu;
	@Resource(name="serlinks")
	private SerLinksIter serlinks;
	
	@RequestMapping("/{categoryname}")
	public ModelAndView category(@PathVariable String categoryname,String page,HttpServletRequest request,HttpServletResponse response){
		String setting = "arcount="+configProperties.getProp("arcount")+"&posts_per_page="+configProperties.getProp("posts_per_page")+"&pagination="+configProperties.getProp("pagination");
		Cookie cookie = new Cookie("setting",setting);
		response.addCookie(cookie);
		if(null==page||page.length()==0){
			page="1";
		}
		
		ModelAndView model = new ModelAndView("category");
		model.addObject("noticeList", serarposts.getNotice(null==configProperties.setinitToMap("notice_size")?1:Integer.parseInt(configProperties.setinitToMap("notice_size"))));
		
		model.addObject("article", serarposts.getArtermsslug(page, categoryname,"category"));
		model.addObject("topmenu",sermenu.guestmenus("topmenu"));	//具体菜单数据
		model.addObject("topnav",sermenu.guestmenus("topnav"));
		
		model.addObject("slnameiden","category");								//当前分类的标识符
		model.addObject("slnameidenname",categoryname);								//当前分类的标识符
		model.addObject("arcount", serarposts.catetagsize(categoryname,"category"));		//商品总数
		model.addObject("page",page);		//当前页码
		
		model.addObject("keywords",configProperties.setinitToMap("web_indexkey"));		//首页关键字
		model.addObject("description",configProperties.setinitToMap("web_indexdes"));		//首页的描述
		model.addObject("brand",configProperties.setinitToMap("web_brandtext"));		//首页的品牌描述
		model.addObject("web_custop",configProperties.setinitToMap("web_custop"));		//顶部样式
		model.addObject("web_cusbut",configProperties.setinitToMap("web_cusbut"));		//底部样式
		
		model.addObject("urliden","fl");		//当前url的某个标识符
		model.addObject("posts_per_page",configProperties.getProp("posts_per_page"));		//每页显示的数据大小
		model.addObject("listlinks", serlinks.allLinks());
		return model;
	}
	
	//具体分类数据
	@RequestMapping("/{categoryname}/page/{pageid}")
	public ModelAndView categorycon(@PathVariable String categoryname,@PathVariable String pageid,HttpServletResponse response){
		String setting = "arcount="+configProperties.getProp("arcount")+"&posts_per_page="+configProperties.getProp("posts_per_page")+"&pagination="+configProperties.getProp("pagination");
		Cookie cookie = new Cookie("setting",setting);
		response.addCookie(cookie);
		
		ModelAndView model = new ModelAndView("category");
		model.addObject("article", serarposts.getArtermsslug(pageid, categoryname,"category"));
		model.addObject("topmenu",sermenu.guestmenus("topmenu"));	//具体菜单数据
		model.addObject("topnav",sermenu.guestmenus("topnav"));
		
		model.addObject("slnameiden","category");								//当前分类的标识符
		model.addObject("slnameidenname",categoryname);								//当前分类的标识符
		model.addObject("arcount", serarposts.catetagsize(categoryname,"category"));		//商品总数
		model.addObject("page",pageid);		//当前页码
		model.addObject("urliden","fl");		//当前url的某个标识符
		model.addObject("posts_per_page",configProperties.getProp("posts_per_page"));		//每页显示的数据大小
		model.addObject("listlinks", serlinks.allLinks());
		return model;
	}
}
