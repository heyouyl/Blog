package springMVC.NLoveB.service.iter;

import java.util.Map;

import springMVC.NLoveB.po.Term_taxonomy;

/**
 * 
 * @ClassName:  SerTermsIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:08:38   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerTermsIter {
	public Map<String,Object> Termsnamelist(String taxonomyname);	//获得分类的List数据List<Map<String,Object>>
	public String getTermsnameid(String taxonomyname);			//获得分类的json数据{"Total":len,Rows:[{},{},{}]}
	
	public String saveupterms(String termsjson,String taxonomy);		//更新或在新增分类 taxonomy控制是分类还是tag或其他
	public String removeterms(String termsids);
	
	public Term_taxonomy categoryuncat();		//获得默认的Term_taxonomy
	public void uprelationships(String[] termsids);//更新这些分类的文章未默认分类，根据termsid
}
