package springMVC.NLoveB.Advicebean;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import springMVC.NLoveB.po.Postmeta;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerEhcacheIter;
import springMVC.NLoveB.service.iter.SerTermsIter;
import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  AdvicePosts   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:57:58   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("abviceposts")
public class AdvicePosts {
	@Resource(name="serarposts")
	private SerArticle_PostsIter serposts;
	@Resource(name="serTerms")
	private SerTermsIter serterms;
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.getAtticleid(..))",returning="returnValue")
	public void updateviews(JoinPoint point, Object returnValue) {		
//      String operType = point.getSignature().getName();		//返回方法名
//      String entity = point.getTarget().getClass().getName();//返回class名
//      Object obj[] = point.getArgs();								//返回参数
		/*
		 * 每次运行一次文章数据，就更新views
		 */
		Object[] args = point.getArgs();
		String spoid = (String) args[0];
		int object = Integer.parseInt((String) args[0]);
		Postmeta postmetaview = serposts.postmetaview(object);
		serposts.addpostmetaview(postmetaview);

		serehcache.cleanKey("articleCache", "postmeta&@"+spoid);

	}
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.delarid(..))",returning="returnValue")
	public void admindelarid(JoinPoint point, Object returnValue) {		
		Object[] args = point.getArgs();
		String object = (String) args[0];
		String restr = (String) returnValue;
		String[] restrs = restr.split(",");
		
		if(restrs[0].equals("delarok")&&restrs[1].equals("publish")){
			String[] values={"articlid&@"+object,"postmeta&@"+object,"newtermsid&@"+serposts.getSetinitValue("index_tuijie")};
			serehcache.cleanKey("articleCache", values);
			serehcache.cleanName("articleListCache");
			serehcache.cleanName("adminpostListCache");
			serehcache.cleanName("userstatusCache");
			//其中一个key是通过Term_taxonomyid获得文章id，可以通过返回Term_taxonomyid来进行部分删除，这里为全部删除
			serehcache.cleanName("taxonomynameCache");
			
		}

	}
	
	//快速修改文章
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.updateArticleFast(..))",returning="returnValue")
	public void adminupArticleFast(JoinPoint point, Object returnValue) {		
		Object[] args = point.getArgs();
		HttpServletRequest request = (HttpServletRequest) args[0];
		String restr = (String) returnValue;
		
		if(restr.equals("uparok")){
			int poid = Integer.parseInt(request.getParameter("arid"));
			
			serehcache.cleanKey("articleCache", "articlid&@"+poid);
			serehcache.cleanName("articleListCache");
			serehcache.cleanName("adminpostListCache");
		}

	}
	
	//修改或新增文章
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.updateArticle(..))",returning="returnValue")
	public void adminupdateArticle(JoinPoint point, Object returnValue) {		
		Object[] args = point.getArgs();
		//获得参数
		HttpServletRequest request = (HttpServletRequest) args[0];
		String spoid = (String) args[1];
		Map<String, Object> mapnew = (Map<String, Object>) returnValue;
		
		//更新后的数据
		//Terms[] t = (Terms[]) mapnew.get("o");
		Posts p = (Posts) mapnew.get("t");
		int poid = p.getPoid();
		if(null==spoid||spoid.length()==0){
			//重新更新
			serposts.updateArticleguid(poid);
			int arcount = Integer.parseInt(configProperties.getProp("arcount"))+1;
			configProperties.setProp("arcount", String.valueOf(arcount));
		}
		
		//清理缓存
		
		serehcache.cleanName("articleListCache");
		serehcache.cleanName("adminpostListCache");
		serehcache.cleanName("userstatusCache");
		serehcache.cleanName("taxonomynameCache");
		
		String[] values={"articlid&@"+poid,"postmeta&@"+poid,"newtermsid&@"+serposts.getSetinitValue("index_tuijie")};
		serehcache.cleanKey("articleCache", values);

		
	}
	//修改或新增页面
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.updatePage(..))",returning="returnValue")
	public void adminupdatePage(JoinPoint point, Object returnValue) {		
		Object[] args = point.getArgs();
		//获得参数
		String spoid = (String) args[1];
		String[] reStr = ((String) returnValue).split(",");

		if(null==spoid||spoid.length()==0){
			if(reStr.length==2){
				//重新更新
				serposts.updateArticleguid(Integer.parseInt(reStr[1]));
			}
		}
		//页面较少，会员与页面共用一个方法，都少，目前无需缓存，后续加上
		
	}
	
	//修改或新增页面
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerArticle_PostsIter.updatenotice(..))",returning="returnValue")
	public void adminupdateNotice(JoinPoint point, Object returnValue) {		
		Object[] args = point.getArgs();
		//获得参数
		String spoid = (String) args[1];
		String[] reStr = ((String) returnValue).split(",");

		if(null==spoid||spoid.length()==0){
			if(reStr.length==2){
				//重新更新
				serposts.updateArticleguid(Integer.parseInt(reStr[1]));
			}
		}
		//页面较少，会员与页面共用一个方法，都少，目前无需缓存，后续加上
		
	}
	
	
	//修改或新增文章
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerCommentIter.upStatusComment(..))",returning="returnValue")
	public void adminupdateStatusComments(JoinPoint point, Object returnValue) {		
		String poid = (String) returnValue;
		String[] poids = poid.split(",");
		if(null==poids||poids.length==0){

		}else{
			int l = poids.length;
			for(int i=0;i<l;i++){
				poids[i] = "getComment"+poids[i];
			}
			serehcache.cleanKey("commentsCache", poids);
		}
	}
}
