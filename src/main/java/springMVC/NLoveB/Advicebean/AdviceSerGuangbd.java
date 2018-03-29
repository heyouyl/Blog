package springMVC.NLoveB.Advicebean;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import springMVC.NLoveB.service.iter.SerEhcacheIter;

/**
 * 
 * @ClassName:  AdviceSerGuangbd   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:58:29   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("adviceSerGuangbd")
public class AdviceSerGuangbd {
	@Resource(name="serehcache")
	private SerEhcacheIter serehcache;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerGuangbdIter.saveupGuangbd(..))",returning="returnValue")
	public void updatelinks(JoinPoint point, Object returnValue) {	
		String[] keys = {"guangbd1","guangbd0","guangbd"};
		serehcache.cleanKey("baseCache", keys);
	}
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerGuangbdIter.delGuangbds(..))",returning="returnValue")
	public void dellinks(JoinPoint point, Object returnValue) {	
		Object[] args = point.getArgs();
		String[] gbdids = args[0].toString().split(",");
		int len = gbdids.length;
		String[] gbdids1 = new String[len+3];
		gbdids1[0]="guangbd1";
		gbdids1[1]="guangbd0";
		gbdids1[2]="guangbd";
		for(int i=3;i<len;i++){
			gbdids1[i] = "guangbdid"+gbdids[i];
		}
		serehcache.cleanKey("baseCache", gbdids1);
		
	}
}
