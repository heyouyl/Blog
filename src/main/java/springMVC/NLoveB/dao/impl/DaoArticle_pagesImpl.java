package springMVC.NLoveB.dao.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springMVC.NLoveB.dao.iter.DaoArticle_pagesIter;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.utils.StringOrObjectUtil;

/**
 * 
 * @ClassName:  DaoArticle_pagesImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:01:45   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Repository("daoarpages")
public class DaoArticle_pagesImpl implements DaoArticle_pagesIter {
	
	//private HibernateTemplate hibernateTemplate = new HibernateUtil().getHibernateTemplate();
	
	@Override
	public <T> List<T> arcount() {
		StringOrObjectUtil.hql = "select count(*) from posts where postatus=:postatus and post_type=:post_type";
		String[] params={"postatus","post_type"};
		Object[] values={"publish","page"};
		return (List<T>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql,params,values);
	}
	
	@Override
	public <T> List<T> daogetpages(int page, int pagesize, int arcount) {
		return daogetDatas(page, pagesize, arcount, "page");
	}

	@Override
	public <T> List<T> daogetnoticess(int page, int pagesize, int arcount) {
		return daogetDatas(page, pagesize, arcount, "notice");
	}

	public <T> List<T> daogetDatas(int page, int pagesize, int arcount,String iden) {
		//获得分页数据
		int fristint = (page==1?0:((page-1)*pagesize));	//获得开始数据 0 10 20 30
		int endint = (arcount-((page-1)*pagesize)<pagesize?(arcount-((page-1)*pagesize)):pagesize);	//获得当前页码的数据，1到10条
		//创建DetachedCriteria对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Posts.class);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("postatus", "publish"));
		criteria.add(Restrictions.eq("post_type", iden));
		criteria.addOrder(Order.desc("poid"));
		
		List<T> list = (List<T>) StringOrObjectUtil.hibernateTemplate.findByCriteria(criteria, fristint, endint);
		return list;
	}
}
