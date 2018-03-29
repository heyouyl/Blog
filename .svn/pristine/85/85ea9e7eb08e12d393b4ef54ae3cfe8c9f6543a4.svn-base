package springMVC.NLoveB.dao.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import springMVC.NLoveB.dao.iter.GuangbdIter;
import springMVC.NLoveB.po.Guangbd;
import springMVC.NLoveB.utils.StringOrObjectUtil;

/**
 * 
 * @ClassName:  GuangbdImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:02:51   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Repository("daoguangbd")
public class GuangbdImpl implements GuangbdIter{
	//private HibernateTemplate hibernateTemplate = new HibernateUtil().getHibernateTemplate();

	@Override
	@Cacheable(value="baseCache",key="'guangbd'+#guangbdtype")
	public <T> List<T> getGuangShowbd(int guangbdtype) {
		StringOrObjectUtil.hql = "select new guangbd(guangbdlocal,guangbdcontext) from guangbd where guangbdis=1 and guangbdtype=:guangbdtype";
		@SuppressWarnings("unchecked")
		List<T> findByNamedParam = (List<T>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "guangbdtype", guangbdtype);
		return findByNamedParam;
	}
	@Override
	@Cacheable(value="baseCache",key="'guangbd'")
	public <T> List<T> getGuangAdminbd() {
		String hql = "from guangbd";
		@SuppressWarnings("unchecked")
		List<T> findByNamedParam = (List<T>) StringOrObjectUtil.hibernateTemplate.find(hql);
		return findByNamedParam;
	}
	@Override
	public <T> String saveOrUpdataGuangbd(T[] t) {
		int length = t.length;
		for(int i=0;i<length;i++){
			StringOrObjectUtil.hibernateTemplate.saveOrUpdate(t[i]);
		}
		return "saveorupok";
	}
	@SuppressWarnings({ "unchecked", "unchecked" })
	@Override
	@Cacheable(value="baseCache",key="'guangbdid'+#guangbdid")
	public <T> T getGuangbd(int guangbdid) {
		// TODO Auto-generated method stub
		return (T) StringOrObjectUtil.hibernateTemplate.get(Guangbd.class, guangbdid);
	}
	@Override
	public String deleteGuangbd(int[] gbdid) {
		for(int i:gbdid){
			StringOrObjectUtil.hibernateTemplate.delete(StringOrObjectUtil.hibernateTemplate.load(Guangbd.class, i));
		}
		return "delok";
	}
}
