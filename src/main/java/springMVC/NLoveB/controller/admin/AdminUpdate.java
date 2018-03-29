package springMVC.NLoveB.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import springMVC.NLoveB.service.iter.MenusIter;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerCommentIter;
import springMVC.NLoveB.service.iter.SerEhcacheIter;
import springMVC.NLoveB.service.iter.SerGuangbdIter;
import springMVC.NLoveB.service.iter.SerLinksIter;
import springMVC.NLoveB.service.iter.SerTermsIter;
import springMVC.NLoveB.service.iter.SerUpOptionsIter;
import springMVC.NLoveB.service.iter.SerUserInfoIter;

/**
 * 
 * @ClassName:  AdminUpdate   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:59:31   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Controller
@RequestMapping("/admin/update")
public class AdminUpdate {
	@Resource(name="serupoptions")
	private SerUpOptionsIter upoptions; 
	@Resource(name="sermenus")
	public MenusIter smenus;
	@Resource(name="serTerms")
	private SerTermsIter serterms;
	@Resource(name="serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	@Resource(name="sercomment")
	private SerCommentIter sercomment;
	@Resource(name="seruserinfo")
	private SerUserInfoIter seruserinfo;
	@Resource(name="serlinks")
	private SerLinksIter serlinks;
	@Resource(name="serGuangbd")
	private SerGuangbdIter serGuangbd;
	
	@RequestMapping(value="/upinitable",method=RequestMethod.POST)
	public @ResponseBody String initable(@RequestParam String data){
		return upoptions.upoptionsinit(data);
	}
	
	@RequestMapping(value="/upmenus",method=RequestMethod.POST)
	public @ResponseBody String upmenus(@RequestParam String saverows,@RequestParam String nowmenus){
		return smenus.updateMenus(saverows, nowmenus);
	}
	@RequestMapping(value="/delmenus",method=RequestMethod.POST)
	public @ResponseBody String delmenus(@RequestParam String saverows,@RequestParam String nowmenus){
		return smenus.deleteMenus(saverows, nowmenus);
	}
	@RequestMapping(value="/addarticle",method=RequestMethod.POST)
	public ModelAndView addarticle(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addarticle");
		modelAndView.addObject("categorylist",serterms.Termsnamelist("category"));
		modelAndView.addObject("adfefd", "操作成功");
		
		serarposts.updateArticle(request,request.getParameter("arid"));
		return modelAndView;
	}
	@RequestMapping(value="/delfl",method=RequestMethod.POST)
	public @ResponseBody String delterms(@RequestParam String flids){
		serterms.removeterms(flids);
		return "deltermsok";
	}
	@RequestMapping(value="/upterms",method=RequestMethod.POST)
	public @ResponseBody String updateterms(@RequestParam String gfl,@RequestParam String taxonomy){
		String saveupterms = serterms.saveupterms(gfl,taxonomy);
		if(saveupterms.equals("false")){
			return "updateerror";
		}else{
			return "saveupok";
		}
	}
	
	@RequestMapping(value="/delselectehcache",method=RequestMethod.POST)
	public @ResponseBody String delselectehcache(String ehcachekey,@RequestParam String ehcachename){
		if(null==ehcachekey||ehcachekey.length()==0){
			//删除所有的
			return serehcache.cleanName(ehcachename);
		}else{
			return serehcache.cleanKey(ehcachename, ehcachekey.split(","));
		}
	}	
	
	//删除一篇文章
	@RequestMapping(value="/delpostid",method=RequestMethod.POST)
	public @ResponseBody String deletelpostid(@RequestParam String poid){

		return serarposts.delarid(poid).split(",")[0];
	}
	
	//快速编辑
	@RequestMapping(value="/uparticle",method=RequestMethod.POST)
	public @ResponseBody String updatearticle(HttpServletRequest request,HttpServletResponse response){
		
		return serarposts.updateArticleFast(request);
	}
	
	//详细编辑
	@RequestMapping(value="/addeditarticle",method=RequestMethod.POST)
	public ModelAndView addeditarticle(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addarticle");
		modelAndView.addObject("categorylist",serterms.Termsnamelist("category"));
		modelAndView.addObject("adfefd", "编辑成功");
		serarposts.updateeditArticle(request);
		return modelAndView;
	}
	
	
	
	
	//修改页面
	@RequestMapping(value="/addpage",method=RequestMethod.POST)
	public ModelAndView addpage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addpage");
		modelAndView.addObject("adfefd", "操作成功");
		
		serarposts.updatePage(request,request.getParameter("arid"));
		return modelAndView;
	}
	
	//编辑页面
	@RequestMapping(value="/addeditpage",method=RequestMethod.POST)
	public ModelAndView addeditpage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addpage");
		modelAndView.addObject("adfefd", "编辑成功");
		serarposts.updateeditnotice(request);
		return modelAndView;
	}
	
	
	//添加公告
	@RequestMapping(value="/addnoticedo",method=RequestMethod.POST)
	public ModelAndView addnotice(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addpage");
		modelAndView.addObject("adfefd", "操作成功");
		
		serarposts.updatenotice(request,request.getParameter("arid"));
		return modelAndView;
	}
	
	//编辑公告
	@RequestMapping(value="/addeditnotice",method=RequestMethod.POST)
	public ModelAndView addeditnotice(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("admin/util/myblog/addpage");
		modelAndView.addObject("adfefd", "编辑成功");
		serarposts.updateeditPage(request);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/upcomment",method=RequestMethod.POST)
	public @ResponseBody String upComment(@RequestParam String iden,@RequestParam String comids){
		return sercomment.upStatusComment(iden, comids);
		
	}
	
	@RequestMapping(value="/upuser",method=RequestMethod.POST)
	public @ResponseBody String upOperateUser(@RequestParam String iden,@RequestParam String datajsons){
		return seruserinfo.upOperateUserInfo(iden, datajsons);
		//iden 1:修改，2为删除
		
	}
	
	@RequestMapping(value="/uplinks",method=RequestMethod.POST)
	public @ResponseBody String upDateLinks(@RequestParam String datajsons){
		return serlinks.saveupLinks(datajsons);
		
	}
	@RequestMapping(value="/dellinks",method=RequestMethod.POST)
	public @ResponseBody String delLinks(@RequestParam String linkids){
		return serlinks.delLinks(linkids);
		
	}
	
	
	@RequestMapping(value="/upguangbd",method=RequestMethod.POST)
	public @ResponseBody String upguangbd(@RequestParam String datajsons){
		return serGuangbd.saveupGuangbd(datajsons);
		
	}
	@RequestMapping(value="/delguangbds",method=RequestMethod.POST)
	public @ResponseBody String delguangbds(@RequestParam String gbdids){
		return serGuangbd.delGuangbds(gbdids);
	}
	
	@RequestMapping(value="/uploadfile",method=RequestMethod.POST)
	public @ResponseBody String uploadfile(HttpServletRequest request){

		return serGuangbd.saveSlide(request);
	}
	
}
