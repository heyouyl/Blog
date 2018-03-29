package springMVC.NLoveB.dao.impl;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import springMVC.NLoveB.dao.iter.DaoUserPermissionsIter;
import springMVC.NLoveB.po.UserMeta;
import springMVC.NLoveB.po.Users;
import springMVC.NLoveB.utils.StringOrObjectUtil;

/**
 * 
 * @ClassName:  DaoUserPermissionsImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:02:57   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Repository("daouserper")
public class DaoUserPermissionsImpl implements DaoUserPermissionsIter{
	//private HibernateTemplate hibernateTemplate = new HibernateUtil().getHibernateTemplate();
	
	@Override
	public <T> List<T> dlogin(T t) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class).setFetchMode("usermeta", FetchMode.SELECT);
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteria.add(Restrictions.eq("username", ((Users)t).getUsername()));
		List<T> list = (List<T>) StringOrObjectUtil.hibernateTemplate.findByCriteria(criteria, 0, 10);
		return list;
	}

	@Override
	public void changedstatus(int userid, int status) {
		Users load = StringOrObjectUtil.hibernateTemplate.get(Users.class, userid);
		if(status<0){
			if(load.getStatus()>=1){
				load.setStatus(load.getStatus()+status);
				StringOrObjectUtil.hibernateTemplate.update(load);
			}
		}else{
			load.setStatus(load.getStatus()+status);
			StringOrObjectUtil.hibernateTemplate.update(load);
		}
	}

	@Override
	public <T> String updateUser(T t) {
		StringOrObjectUtil.hibernateTemplate.saveOrUpdate(t);
		return "updateok";
	}
	
	@Override
	public <T> String updateUser(String iden,T[] t) {
		if("-1".equals(iden)){
			delUsers(t);
		}
		for(T u:t){
			StringOrObjectUtil.hibernateTemplate.saveOrUpdate(u);
		}
		return "updateok";
	}

	@Override
	public <T> T useridinfo(int userid) {
		// TODO Auto-generated method stub
		Users users = StringOrObjectUtil.hibernateTemplate.get(Users.class, userid);
		return (T) users;
	}

	@Override
	public String updatePassword(int userid, String newpass) {
		Users load = StringOrObjectUtil.hibernateTemplate.load(Users.class, userid);
		load.setUserpass(newpass);
		StringOrObjectUtil.hibernateTemplate.update(load);
		return "uppassok";
	}

	@Override
	public <T> List<T> useremail(String username, String useremail) {
		StringOrObjectUtil.hql = "from users user where user.username=:username or user.useremail=:useremail";
		String[] params = {"username","useremail"};
		Object[] values = {username,useremail};
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, params, values);
		return (List<T>) findByNamedParam;
	}

	@Override
	public <T> List<T> username(String username) {
		StringOrObjectUtil.hql = "from users user where user.username=:username";
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "username", username);
		return (List<T>) findByNamedParam;
	}

	@Override
	public <T> List<T> useremail(String useremail) {
		StringOrObjectUtil.hql = "from users user where user.useremail=:useremail";
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "useremail", useremail);
		return (List<T>) findByNamedParam;
	}

	@Override
	public <T> List<T> alllostpass() {
		StringOrObjectUtil.hql = "from usermeta um where um.umetakey=:umetakey";
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "umetakey", "lostpass");
		return (List<T>) findByNamedParam;
	}

	@Override
	public void delalllostpass(String[] umids) {
		int len = umids.length;
		for(int i=0;i<len;i++){
			UserMeta load = StringOrObjectUtil.hibernateTemplate.load(UserMeta.class, Integer.parseInt(umids[i]));
			StringOrObjectUtil.hibernateTemplate.delete(load);
		}
		
	}

	@Override
	public <T> List<T> userlostpass(int userid) {
		//Users load = StringOrObjectUtil.hibernateTemplate.load(Users.class, userid);
		Users user = new Users();
		user.setUserid(userid);
		DetachedCriteria criteria = DetachedCriteria.forClass(UserMeta.class);
		criteria.add(Restrictions.eq("uuserid", user))
			.add(Restrictions.eq("umetakey", "lostpass"));
		return (List<T>) StringOrObjectUtil.hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public <T> List<T> usercount() {
		String hql = "select count(*) from users";
		return (List<T>) StringOrObjectUtil.hibernateTemplate.find(hql);
	}
	
	@Override
	public <T> List<T> allUser(int page, int pagesize,int usercount) {		
		//获得分页数据
		int fristint = (page==1?0:((page-1)*pagesize));	//获得开始数据 0 10 20 30
		//arcount 是分类的文章大小，后期需要修改，因此时是所有的文章大小
		int endint = (usercount-((page-1)*pagesize)<pagesize?(usercount-((page-1)*pagesize)):pagesize);	//获得当前页码的数据，1到10条
		
		DetachedCriteria criteria = DetachedCriteria.forClass(Users.class).setFetchMode("usermeta", FetchMode.SELECT);
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<T> list = (List<T>) StringOrObjectUtil.hibernateTemplate.findByCriteria(criteria, fristint, endint);
		return list;
	}

	@Override
	public <T> String delUsers(T[] t) {
		int len = t.length;
		for(int i=0;i<len;i++){
			StringOrObjectUtil.hibernateTemplate.delete(t[i]);
		}
		return "upuserok";
	}
}
