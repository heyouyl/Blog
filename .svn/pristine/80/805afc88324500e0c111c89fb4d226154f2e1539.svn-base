package springMVC.NLoveB.utils;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 
 * @ClassName:  HibernateUtil   
 * @Description:TODO(HibernateTemplate工厂)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:17:11   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class HibernateUtil {
	
	private static HibernateTemplate hibernateTemplate;

	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
}
