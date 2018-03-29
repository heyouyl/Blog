package springMVC.NLoveB.dao.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import springMVC.NLoveB.dao.iter.DaoLinksIter;
import springMVC.NLoveB.po.Links;
import springMVC.NLoveB.utils.StringOrObjectUtil;
/**
 * 
 * @ClassName:  DaoLinksImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:03:11   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Repository("daoLinks")
public class DaoLinksImpl implements DaoLinksIter {
	//private HibernateTemplate hibernateTemplate = new HibernateUtil().getHibernateTemplate();

	@Override
	@Cacheable(value="baseCache",key="'allLinks'+'&@'+#visible")
	public <T> List<T> allLinks(String visible) {
		StringOrObjectUtil.hql = "from links  where linkvisible=:visible";
		return (List<T>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "visible", visible);
	}

	@Override
	public <T> List<T> allLinks() {
		StringOrObjectUtil.hql = "from links";
		return (List<T>) StringOrObjectUtil.hibernateTemplate.find(StringOrObjectUtil.hql);
	}
	
	@Override
	public <T> T getLinks(int linkid) {
		return (T) StringOrObjectUtil.hibernateTemplate.get(Links.class, linkid);
	}

	@Override
	public <T> String updateLinks(T[] t) {
		int length = t.length;
		for(int i=0;i<length;i++){
			StringOrObjectUtil.hibernateTemplate.saveOrUpdate(t[i]);
		}
		return "saveupok";
	}

	@Override
	public String deleteLinks(int[] linkids) {
		for(int i:linkids){
			StringOrObjectUtil.hibernateTemplate.delete(StringOrObjectUtil.hibernateTemplate.load(Links.class, i));
		}
		return "dellinkok";
	}

}
