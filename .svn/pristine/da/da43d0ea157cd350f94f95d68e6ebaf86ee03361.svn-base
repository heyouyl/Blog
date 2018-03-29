package springMVC.NLoveB.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import springMVC.NLoveB.dao.iter.DaoArticle_pagesIter;
import springMVC.NLoveB.dao.iter.DaoArticle_postsIter;
import springMVC.NLoveB.po.Postmeta;
import springMVC.NLoveB.po.Posts;
import springMVC.NLoveB.service.iter.SerArticle_PageIter;
import springMVC.NLoveB.utils.configProperties;
/*
 * page的设计
 * 是否需要头部
 * 是否需要底部
 * 是否需要侧边栏
 * 是否使用模版
 * 是否开启评论
 */
/**
 * 
 * @ClassName:  SerArticle_PageImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:56   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serarpages")
public class SerArticle_PageImpl implements SerArticle_PageIter {
	@Resource(name="daoarpages")
	private DaoArticle_pagesIter daopage;
	@Resource(name="daoarposts")
	private DaoArticle_postsIter darpost;
	
	@Override
	public int getCount() {
		List<Number> arcount = daopage.arcount();
		return arcount.get(0).intValue();
	}
	
	@Override
	public String adminpgesjson(String page, String pagesize) {
		int ipage = 0;
		int ipagesize = 0;
		if(null==page||page.length()==0){
			ipage = 1;
		}else{
			ipage=Integer.parseInt(page);
		}
		if(null==pagesize||pagesize.length()==0){
			ipagesize = Integer.parseInt(configProperties.getProp("posts_per_page"));
		}else{
			ipagesize=Integer.parseInt(pagesize);
		}
		
		
		int arcount = getCount();
		
		List<Posts> adminpage = daopage.daogetpages(ipage, ipagesize, arcount);

		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		for(Posts p:adminpage){
			map = new HashMap<String,Object>();
			int pid = p.getPoid();
			map.put("arid", pid);//文章的id
			map.put("arname", p.getPotitle());		//文章的名字
			map.put("arstatus", p.getPostatus());		//文章的状态
			map.put("commentstatus", p.getPocommentstatus());	//文章评论状态
			map.put("pass", p.getPopass());			//文章的密码
			map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getPodate()));//文章的时间
			map.put("aruid", p.getUserid());
			map.put("arurl", p.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+pid+".html");
			list.add(map);
		}
		hashMap.put("Rows",list);
		hashMap.put("Total", arcount);
		return JSON.toJSONString(hashMap);
	}

	@Override
	public Map<String, Object> getPageid(String pageid) {
		Map<String,Object> map = new HashMap<String,Object>();
		Posts post = darpost.articlid(Integer.parseInt(pageid));
		
		int poid = post.getPoid();
		map.put("arid", poid);//页面的id
		map.put("arurl", post.getPoname()+".html");//页面的url
		map.put("artitle", post.getPotitle());		//页面的title
		map.put("commentcount", post.getPocomment_count());		//评论总数
		map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(post.getPodate()));//页面的时间
		map.put("aruser", post.getUserid());//页面的作者
		map.put("content", post.getPocontent());//页面的内容
		
		//获得postmeta 已加入缓存
		List<Postmeta> pmlist = darpost.postmeta(poid);
		
		/*
		 * 页面的元数据
		 * views description keywords plugs
		 * plugs {ptop:true,pbottom:true,pleft:xxxx,pright:false}
		 * top和bottom只有开启和关闭
		 * left：一般是自定义菜单
		 * right：开启或打开
		 */
		for(Postmeta p:pmlist){
			String metakey = p.getMetakey();
			if(metakey.equals("views")||metakey.equals("description")||metakey.equals("keywords")){
				map.put(metakey, p.getMetavalue());
			}
			if(metakey.equals("plugs")){
				if(null==p.getMetavalue() || p.getMetavalue().length()==0){
					map.put("ptop","true");		//顶部显示
					map.put("pbottom","true");	//底部显示
					map.put("pleft","false");	//左侧不显示
					map.put("pright","false");	//右侧不显示
				}else{
					Map<String, String> parseObject = JSON.parseObject(metakey, new TypeReference<Map<String, String>>(){});
					for(Map.Entry<String, String> m:parseObject.entrySet()){
						map.put(m.getKey(), m.getValue());
					}
				}
			}
		}
		return map;
	}

	@Override
	public String adminnoticejson(String page, String pagesize) {
		int ipage = 0;
		int ipagesize = 0;
		if(null==page||page.length()==0){
			ipage = 1;
		}else{
			ipage=Integer.parseInt(page);
		}
		if(null==pagesize||pagesize.length()==0){
			ipagesize = Integer.parseInt(configProperties.getProp("posts_per_page"));
		}else{
			ipagesize=Integer.parseInt(pagesize);
		}
		
		
		int arcount = getCount();
		
		List<Posts> adminpage = daopage.daogetnoticess(ipage, ipagesize, arcount);

		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		for(Posts p:adminpage){
			map = new HashMap<String,Object>();
			int pid = p.getPoid();
			map.put("arid", pid);//文章的id
			map.put("arname", p.getPotitle());		//文章的名字
			map.put("arstatus", p.getPostatus());		//文章的状态
			map.put("commentstatus", p.getPocommentstatus());	//文章评论状态
			map.put("pass", p.getPopass());			//文章的密码
			map.put("ardate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(p.getPodate()));//文章的时间
			map.put("aruid", p.getUserid());
			map.put("arurl", p.getPodate().toString().replace("-", "_").substring(0, 7)+"_"+pid+".html");
			list.add(map);
		}
		hashMap.put("Rows",list);
		hashMap.put("Total", arcount);
		return JSON.toJSONString(hashMap);
	}

}
