package springMVC.NLoveB.Advicebean;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerEhcacheIter;
import springMVC.NLoveB.service.iter.SerTermsIter;
import springMVC.NLoveB.utils.EhcacheUtil;

/**
 * 
 * @ClassName:  AdviceSerTerms   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:58:18   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("abviceterms")
public class AdviceSerTerms {
	@Resource(name="serarposts")
	private SerArticle_PostsIter serposts;
	@Resource(name="serTerms")
	private SerTermsIter serterms;
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerTermsIter.saveupterms(..))",returning="returnValue")
	public void updateterms(JoinPoint point, Object returnValue) {		
//      String operType = point.getSignature().getName();		//返回方法名
//      String entity = point.getTarget().getClass().getName();//返回class名
//      Object obj[] = point.getArgs();								//返回参数
		/*
		 * 更新分类
		 * 将term_relationships的object_id缓存删掉
		 * 同时删除菜单的缓存（顶部菜单，导航菜单，下拉菜单，可以先进行验证，达到值删除部分缓存的目的）
		 */
		String ttid =  (String) returnValue;
		StringBuilder sb = new StringBuilder(1);
		if(ttid.equals("true")){
			//表示更新成功
			serehcache.cleanName("taxonomynameCache");
		}else if(ttid.equals("false")){
			//更新失败
		}else{
			//临时注释掉下面的信息
			String[] ttids = ttid.split(",");
			int len = ttids.length;
			for(int i=0;i<len;i++){
				//得到每一个分类的文章id 后面分别清除缓存
				int[] poids = serposts.categoryidToObject(Integer.parseInt(ttids[i]));
				int L = poids.length;
				for(int pid:poids){
					sb.append("articlid&@"+pid+",");
					sb.append("postmeta&@"+pid+",");
				}
			}
			if(null==sb||sb.length()==0){
				//该分类没有文章
				serehcache.cleanName("taxonomynameCache");
			}else{
				String substring = sb.substring(0, sb.length()-1);
				serehcache.cleanKey("articleCache", substring.split(","));
				serehcache.cleanName("articleListCache");
				serehcache.cleanName("taxonomynameCache");
			}
			
			//EhcacheUtil.cleanAll();
		}
	}
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerTermsIter.removeterms(..))",returning="returnValue")
	public void delterms(JoinPoint point, Object returnValue) {		
//      String operType = point.getSignature().getName();		//返回方法名
//      String entity = point.getTarget().getClass().getName();//返回class名
//      Object obj[] = point.getArgs();								//返回参数
		
		/*
		 * 删除分类
		 * 将term_relationships的taxonomy_id设置为1（即未分类）
		 * 同时删除object_id的缓存
		 * 同时删除菜单的缓存（顶部菜单，导航菜单，下拉菜单，可以先进行验证，达到值删除部分缓存的目的）
		 */
		serehcache.cleanName("articleCache");
		serehcache.cleanName("taxonomynameCache");
	}
}
