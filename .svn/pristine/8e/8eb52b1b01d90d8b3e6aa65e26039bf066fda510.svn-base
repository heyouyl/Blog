package springMVC.NLoveB.Advicebean;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import springMVC.NLoveB.service.iter.SerEhcacheIter;

/**
 * 
 * @ClassName:  AdviceOptions   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:57:52   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("adviceOptions")
public class AdviceOptions {
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerGuangbdIter.saveSlide(..))",returning="returnValue")
	public void updatelinks(JoinPoint point, Object returnValue) {
		serehcache.cleanName("dautooptionOpname");
	}
}
