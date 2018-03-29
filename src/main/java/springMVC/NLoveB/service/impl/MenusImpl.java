package springMVC.NLoveB.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import springMVC.NLoveB.dao.iter.DaoOptionsIter;
import springMVC.NLoveB.po.Options;
import springMVC.NLoveB.service.iter.MenusIter;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  MenusImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:21   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("sermenus")
public class MenusImpl implements MenusIter{
	@Resource(name="daooptions")
	private DaoOptionsIter<Options> doptions;
	
	@SuppressWarnings("unchecked")
	@Override
	public String updateMenus(String saverows,String nowmenus) {
		//获得菜单项的key
		String web_menuopsition = (String) JSON.parseObject(configProperties.getProp("setinit"), Map.class).get("web_menuopsition");
		Map<String,String> map =JSON.parseObject(web_menuopsition,Map.class);
		//得到菜单的key
		String mapkey = null;
		for(Map.Entry<String, String> entry:map.entrySet()){
			if(entry.getValue().equals(nowmenus)){
				mapkey=entry.getKey();
				break;
			}
		}
		
		//获得map——将当前传递过来的数据保存为一个map
		Map<String,String> map1 = JSON.parseObject(saverows, Map.class);
		//获得原始的菜单项/菜单属性[{},{},{}]
		List<Map<String,String>> list;
		if(null==configProperties.getProp(mapkey)||configProperties.getProp(mapkey).length()==0){
			list = new ArrayList<Map<String,String>>();
		}else{
			list = JSON.parseObject(configProperties.getProp(mapkey),List.class);
		}
		list.add(map1);
		configProperties.setProp(mapkey, JSON.toJSONString(list));
		doptions.saveupdateop(mapkey, configProperties.getProp(mapkey));
		return "saveok";
	}
	@Override
	public String deleteMenus(String saverows,String nowmenus){
		//获得菜单项的key
		String web_menuopsition = (String) JSON.parseObject(configProperties.getProp("setinit"), Map.class).get("web_menuopsition");
		Map<String,String> map =JSON.parseObject(web_menuopsition,Map.class);
		//得到菜单的key
		String mapkey = null;
		for(Map.Entry<String, String> entry:map.entrySet()){
			if(entry.getValue().equals(nowmenus)){
				mapkey=entry.getKey();
				break;
			}
		}
		
		configProperties.setProp(mapkey, saverows);
		doptions.saveupdateop(mapkey, saverows);
		return "deleteok";
	}
	@Override
	public List<Map<String, String>> guestmenus(String menuname) {
		String prop = configProperties.getProp(menuname);
		//后期进行判断，如果没有就从数据库读取
		List<Map<String,String>> list = JSON.parseObject(prop,List.class);

		return list;
	}

	@Override
	public String getmenudate(String menusname) {
		//获得菜单项的key
		String web_menuopsition = (String) JSON.parseObject(configProperties.getProp("setinit"), Map.class).get("web_menuopsition");
		Map<String,String> map =JSON.parseObject(web_menuopsition,Map.class);
		//得到菜单的key
		String mapkey = null;
		for(Map.Entry<String, String> entry:map.entrySet()){
			if(entry.getValue().equals(menusname)){
				mapkey=entry.getKey();
				break;
			}
		}	
		//获得具体的菜单数据,并将其转换为list<map>
		List<Map<String,String>> list = JSON.parseObject(configProperties.getProp(mapkey),List.class);
		//组建json所需要的map
		Map<String,Object> jsonmap = new HashMap<String,Object>();
		jsonmap.put("Rows", list);
		jsonmap.put("Total", list.size());
		return JSON.toJSONString(jsonmap);
	}

}
