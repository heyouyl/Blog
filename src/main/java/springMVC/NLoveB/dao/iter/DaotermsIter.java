package springMVC.NLoveB.dao.iter;

import java.util.List;
import java.util.Map;

import springMVC.NLoveB.po.Term_relationships;
import springMVC.NLoveB.po.Term_taxonomy;
import springMVC.NLoveB.po.Terms;

/**
 * 
 * @ClassName:  DaotermsIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:03:44   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface DaotermsIter {
	public <T> List<T> getterms(String taxonomyname); 
	
	public Term_taxonomy[] saveGetTermstagsid(Terms[] termss); //获得标签的id termtaxonomyid 用于添加文章时的tags关联
	public Term_taxonomy getTermscategoryid(int categoryid); //获得分类的id termtaxonomyid 用于添加文章时的分类关联
	public Map<String,List<Terms>> isGetTermtags(String ... name);	//查找tags是否存在

	public Terms getTermsid(int categoryid);				//根据分类id获得分类实体
	
	public String saveorupdate(Terms[] termss);
	public String deleterms(int[] termsid);
	
	public <T> List<T> relationshipsObject(int categoryid);//通过categoryid 获得文章的id

	public <T> List<T> relationships(int taxonomyid);				//根据分类id获得relationships
	public void updaterelationships(Term_relationships[] tr);				//更新
	public Terms uncategorized();		//获得默认的未分类

	public Term_taxonomy getTerm_taxonomy(int categoryid);	//根据Term_taxonomyid获得Term_taxonomy实体
	public <T> List<T> getTerm_taxonomy(String termsname, String categroy);//获得当前termsname categroy 的Term_taxonomy实体
	
	public void updateTermsSubOne(Term_taxonomy[] oldtt);//获得当前termsname categroy 的Term_taxonomy实体

	void deleterelationships(Term_relationships[] oldtr);//暂时无用

}
