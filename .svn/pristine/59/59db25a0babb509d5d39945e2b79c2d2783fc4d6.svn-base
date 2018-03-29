package springMVC.NLoveB.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import springMVC.NLoveB.dao.iter.DaoLinksIter;
import springMVC.NLoveB.po.Links;
import springMVC.NLoveB.service.iter.SerLinksIter;
/**
 * 
 * @ClassName:  SerLinksImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:08   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serlinks")
public class SerLinksImpl implements SerLinksIter {
	@Resource(name="daoLinks")
	private DaoLinksIter dlinks;
	
	@Override
	public List<Links> allLinks() {
		return dlinks.allLinks("Y");
	}
	@Override
	public String allAdminLinks() {
		List<Links> allLinks = dlinks.allLinks();
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		int len = allLinks.size();
		//url name visible note rating
		for(int i=0;i<len;i++){
			map = new HashMap<String,Object>();
			map.put("linkurlid", allLinks.get(i).getLinkid());
			map.put("linkurl", allLinks.get(i).getLinkurl());
			map.put("linkname", allLinks.get(i).getLinkname());
			map.put("linkvisible", allLinks.get(i).getLinkvisible());
			map.put("linknote", allLinks.get(i).getLinknotes());
			map.put("linkrating", allLinks.get(i).getLinkrating());
			list.add(map);
		}
		
		hashMap.put("Rows",list);
		hashMap.put("Total", len);
		return JSON.toJSONString(hashMap);
	}
	@Override
	public String saveupLinks(String datajsons) {
		//获得一个JSONArray数组
		JSONArray jsonArray = JSONArray.parseArray(datajsons);
		int L = jsonArray.size();
		
		//定义我们的Object
		Links[] links = new Links[L];
		Links t = null;
		
		//for循环，将每一个对象集合封装在一个map中
		for(int i =0;i<L;i++){
			//初始每一个对象
//			termss[i] = new Terms();
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if((jsonObject.getString("__status")).equals("update")){
				/*
				 * 更新分类
				 * 将term_relationships的object_id缓存删掉
				 * 同时删除菜单的缓存（顶部菜单，导航菜单，下拉菜单，可以先进行验证，达到值删除部分缓存的目的）
				 */
				//获得Terms实体
				Links link = dlinks.getLinks(jsonObject.getIntValue("linkurlid"));
				link.setLinkurl(jsonObject.getString("linkurl"));
				link.setLinkname(jsonObject.getString("linkname"));
				link.setLinkvisible(jsonObject.getString("linkvisible"));
				link.setLinknotes(jsonObject.getString("linknote"));
				link.setLinkrating(Integer.parseInt(jsonObject.getString("linkrating")));
				link.setLinkupdated(new Date());
				links[i] = link;
			}else if((jsonObject.getString("__status")).equals("add")){
				t = new Links();
				t.setLinkurl(jsonObject.getString("linkurl"));
				t.setLinkname(jsonObject.getString("linkname"));
				t.setLinkvisible(jsonObject.getString("linkvisible"));
				t.setLinknotes(jsonObject.getString("linknote"));
				t.setLinkrating(Integer.parseInt(jsonObject.getString("linkrating")));
				t.setLinkupdated(new Date());
				links[i] = t;
			}else{
				return null;
			}
		}
		
		return dlinks.updateLinks(links);
	}
	@Override
	public String delLinks(String linkids) {
		if(null==linkids||linkids.length()==0){
			
		}else{
			String[] links = linkids.split(",");
			int len = links.length;
			int[] linkid = new int[len];
			for(int i=0;i<len;i++){
				linkid[i]=Integer.parseInt(links[i]);
			}
			return dlinks.deleteLinks(linkid);
		}
		return "错误";
	}
}
