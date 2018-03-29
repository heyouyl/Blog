package springMVC.NLoveB.Advicebean;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import springMVC.NLoveB.service.iter.SerEhcacheIter;

/**
 * 
 * @ClassName:  AdviceSerLinks   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:58:24   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("abvicelinks")
public class AdviceSerLinks {
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLinksIter.saveupLinks(..))",returning="returnValue")
	public void updatelinks(JoinPoint point, Object returnValue) {	
		String[] keys = {"allLinks&@Y","allLinks&@N"};
		serehcache.cleanKey("baseCache", keys);
	}
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLinksIter.delLinks(..))",returning="returnValue")
	public void dellinks(JoinPoint point, Object returnValue) {		
		String[] keys = {"allLinks&@Y","allLinks&@N"};
		serehcache.cleanKey("baseCache", keys);
	}
}
