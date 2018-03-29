package springMVC.NLoveB.dao.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;

import springMVC.NLoveB.dao.iter.DaotermsIter;
import springMVC.NLoveB.po.Term_relationships;
import springMVC.NLoveB.po.Term_taxonomy;
import springMVC.NLoveB.po.Terms;
import springMVC.NLoveB.utils.StringOrObjectUtil;

/**
 * 
 * @ClassName:  DaotermsImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:03:02   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Repository("daoterms")
public class DaotermsImpl implements DaotermsIter {
	//private HibernateTemplate hibernateTemplate = new HibernateUtil().getHibernateTemplate();
	
	@Override
	/*
	 * @see springMVC.NLoveB.dao.iter.DaotermsIter#getterms(java.lang.String)
	 * 获得全部的分类，以数据库分类值标识：如category或post_tag等(non-Javadoc)
	 * ehcache 永久存在
	 * 缓存条件：添加或删除，或修改分类时删除该缓存
	 */
	@Cacheable(value="taxonomynameCache",key="'taxonomyname'+'&@'+#taxonomyname")
	public <T> List<T> getterms(String taxonomyname) {
		StringOrObjectUtil.hql = "select t.termid from term_taxonomy t where t.taxonomy=:taxonomy";
		List<T> findByNamedParam = (List<T>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "taxonomy", taxonomyname);
		return findByNamedParam;
	}

	@Override
	public Term_taxonomy[] saveGetTermstagsid(Terms[] termss) {
		Term_taxonomy[] ot = new Term_taxonomy[termss.length];
		for(int i=0;i<termss.length;i++){
//			Term_taxonomy tt = new Term_taxonomy();
//			tt.setTermid(termss[i]);
//			tt.setTaxonomy("post_tag");
			StringOrObjectUtil.hql = "select t from term_taxonomy t where t.termid=:termid and t.taxonomy=:taxonomy";
			String[] params={"termid","taxonomy"};
			Object[] values={termss[i],"post_tag"};
//			Term_taxonomy term_taxonomy = StringOrObjectUtil.hibernateTemplate.findByExample(tt).get(0);
			@SuppressWarnings("unchecked")
			List<Term_taxonomy> term_taxonomys = (List<Term_taxonomy>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, params, values);
			term_taxonomys.get(0).setCount(term_taxonomys.get(0).getCount()+1);
			ot[i] = term_taxonomys.get(0);
		}
		return ot;
	}

	@Override
	public Term_taxonomy getTermscategoryid(int categoryid) {	
		StringOrObjectUtil.hql = "select t from term_taxonomy t where t.termid=:termid and t.taxonomy=:taxonomy";
		String[] params={"termid","taxonomy"};
		Object[] values={StringOrObjectUtil.hibernateTemplate.load(Terms.class, categoryid),"category"};

		List<Term_taxonomy> term_taxonomys = (List<Term_taxonomy>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, params, values);
		if(null==term_taxonomys||term_taxonomys.size()==0){
			return null;
		}else{
			Term_taxonomy term_taxonomy = term_taxonomys.get(0);
			term_taxonomy.setCount(term_taxonomy.getCount()+1);
			return term_taxonomy;
		}
	}
	
	//根据分类id获得分类实体termsidCache
	@Cacheable(value="taxonomynameCache",key="'getTermsid'+'&@'+#categoryid")
	@Override
	public Terms getTermsid(int categoryid) {	
		return StringOrObjectUtil.hibernateTemplate.get(Terms.class, categoryid);
	}
	
	@Override
	@Cacheable(value="taxonomynameCache",key="'getTerm_taxonomy'+'&@'+#categoryid")
	public Term_taxonomy getTerm_taxonomy(int categoryid) {	
		Term_taxonomy term_taxonomy = StringOrObjectUtil.hibernateTemplate.get(Term_taxonomy.class, categoryid);
		return term_taxonomy;
	}
	
	//查找是否存在terms return Map<String,list>
	@Override
	public Map<String,List<Terms>> isGetTermtags(String... name) {
		List<Terms> noterms = new ArrayList<Terms>();
		List<Terms> yesterms = new ArrayList<Terms>();
		Map<String,List<Terms>> map = new HashMap<String,List<Terms>>();

		for(int i=0;i<name.length;i++){
			Terms t = new Terms();
			Term_taxonomy tt = new Term_taxonomy();
			
			t.setName(name[i]);
			tt.setTaxonomy("post_tag");
			tt.setDescription("");
			
			t.setTerm_taxonomy(tt);
			tt.setTermid(t);

			//使用hql查询
			StringOrObjectUtil.hql = "select ts from terms ts,term_taxonomy tt where ts.name=:name and tt.termid = ts.termid and tt.taxonomy=:taxonomy";
			String[] params = {"name","taxonomy"};
			Object[] values = {name[i],"post_tag"};
			List<Terms> list= (List<Terms>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, params, values);
			
			//List<Terms> list = StringOrObjectUtil.hibernateTemplate.findByExample(t);
			
			if(null==list||list.size()==0){
				try {
					t.setSlug(URLEncoder.encode(name[i], "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				noterms.add(t);
			}else{
				yesterms.add(list.get(0));
			}
			map.put("noterms", noterms);
			map.put("yesterms", yesterms);
		}
		return map;
	}

	@Override
	public String saveorupdate(Terms[] termss) {
		int length = termss.length;
		for(int i=0;i<length;i++){
			StringOrObjectUtil.hibernateTemplate.saveOrUpdate(termss[i]);
		}
		return "saveorupok";
	}

	@Override
	public String deleterms(int[] termsid) {
		int length = termsid.length;
		String taxonomyid="";
		for(int i=0;i<length;i++){
			Terms load = StringOrObjectUtil.hibernateTemplate.load(Terms.class, termsid[i]);
			taxonomyid = taxonomyid + load.getTerm_taxonomy().getTermtaxonomyid()+",";
			StringOrObjectUtil.hibernateTemplate.delete(load);
		}
		return taxonomyid;
	}

	//根据categoryid获得文章的id
	@Override
	@Cacheable(value="taxonomynameCache",key="'relationshipsObject'+'&@'+#categoryid")
	public <T> List<T> relationshipsObject(int categoryid) {
		StringOrObjectUtil.hql = "select tr.objectid.poid from term_relationships tr left join tr.taxonomyid tt where tt.termtaxonomyid=:termtaxonomyid and tt.taxonomy=:taxonomy";
		String[] params={"termtaxonomyid","taxonomy"};
		Object[] values={categoryid,"category"};
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, params, values);
		return (List<T>) findByNamedParam;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="taxonomynameCache",key="'relationships'+'&@'+#termsid")
	public <T> List<T> relationships(int termsid) {
		
		//分类
		StringOrObjectUtil.hql = "select tr from term_relationships tr left join tr.taxonomyid tt where tt.termid.termid=:termsid";
		List<?> findByNamedParam = StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql, "termsid", termsid);
		
		return (List<T>) findByNamedParam;
	}

	@Override
	@Cacheable(value="taxonomynameCache",key="'uncategorized'")
	public Terms uncategorized() {
		// TODO Auto-generated method stub
		return StringOrObjectUtil.hibernateTemplate.get(Terms.class, 1);
	}

	@Override
	public void updaterelationships(Term_relationships[] tr) {
		Term_taxonomy term_taxonomy = uncategorized().getTerm_taxonomy();
		int count = term_taxonomy.getCount();
		int len  = tr.length;
		for(int i=0;i<len;i++){
			//获得默认的Term_taxonomy id
			final int uncateid = term_taxonomy.getTermtaxonomyid();
			//获得poid
			final int poid = tr[i].getObjectid().getPoid();
			//获得taxonomyid
			final int taxonomyid = tr[i].getTaxonomyid().getTermtaxonomyid();
			
			//修改服务要求的数据
			StringOrObjectUtil.hibernateTemplate.execute(new HibernateCallback<Object>(){
				String tempsql = "update term_relationships set taxonomyid='"+uncateid+"' where objectid='"+poid+"' and taxonomyid='"+taxonomyid+"'";
				@Override
				public Object doInHibernate(Session arg0) throws HibernateException {
					arg0.createQuery(tempsql).executeUpdate();

		            return null;
				}
				
			});
			count=count+1;
			term_taxonomy.setCount(count);
			//更新默认的Term_taxonomy的数据
			StringOrObjectUtil.hibernateTemplate.update(term_taxonomy);
		}
		
	}
	
	//暂时无用
	@Override
	public void deleterelationships(Term_relationships[] oldtr) {
		int L = oldtr.length;
		for(int i=0;i<L;i++){
			StringOrObjectUtil.hibernateTemplate.delete(oldtr[i]);
		}
	}
	
	//主要是获得Term_taxonomy的count 这里是全部获得
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable(value="taxonomynameCache",key="'getTerm_taxonomy'+'&@'+#termsname+'&@'+#categroy")
	public <T> List<T> getTerm_taxonomy(String termsname, String categroy) {
		//StringOrObjectUtil.hql = "select tt from terms t inner join t.term_taxonomy tt where t.slug=:termsname and tt.taxonomy=:categroy";
		StringOrObjectUtil.hql = "select tt from term_taxonomy tt left join tt.termid t where t.slug=:termsname and tt.taxonomy=:categroy";
		String[] params={"termsname","categroy"};
		Object[] values={termsname,categroy};
		return (List<T>) StringOrObjectUtil.hibernateTemplate.findByNamedParam(StringOrObjectUtil.hql,params,values);
	}

	@Override
	public void updateTermsSubOne(Term_taxonomy[] oldtt) {
		for(Term_taxonomy tt:oldtt){
			Term_taxonomy load = StringOrObjectUtil.hibernateTemplate.load(Term_taxonomy.class, tt.getTermtaxonomyid());
			if(null==load){
				//没有这个数据
			}else{
				load.setCount(load.getCount()-1);
				StringOrObjectUtil.hibernateTemplate.update(load);
			}
		}
		
	}

}
