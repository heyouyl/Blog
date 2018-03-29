package springMVC.NLoveB.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import springMVC.NLoveB.dao.iter.DaoOptionsIter;
import springMVC.NLoveB.dao.iter.GuangbdIter;
import springMVC.NLoveB.po.Guangbd;
import springMVC.NLoveB.po.Options;
import springMVC.NLoveB.service.iter.SerGuangbdIter;
import springMVC.NLoveB.utils.EnumPath;
/**
 * 
 * @ClassName:  SerGuangbdImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:13   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serGuangbd")
public class SerGuangbdImpl implements SerGuangbdIter{
	@Resource(name="daoguangbd")
	private GuangbdIter guangbd;
	@Resource(name="daooptions")
	private DaoOptionsIter<Options> doptions;
	
	@Override
	public String getGuangbdJsonAll() {
		List<Guangbd> guangAdminbd = guangbd.getGuangAdminbd();
		Map<String,Object> hashMap = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		int len = guangAdminbd.size();
		//url name visible note rating
		for(int i=0;i<len;i++){
			map = new HashMap<String,Object>();
			map.put("guangbdid", guangAdminbd.get(i).getGuangbdid());		//编号
			map.put("guangbdlocal", guangAdminbd.get(i).getGuangbdlocal());		//位置
			map.put("guangbddes", guangAdminbd.get(i).getGuangbddes());	//说明
			map.put("guangbdtype", guangAdminbd.get(i).getGuangbdtype());		//类型
			map.put("guangbdcontext", guangAdminbd.get(i).getGuangbdcontext());	//广告内容
			map.put("guangbdis", guangAdminbd.get(i).getGuangbdis());		//是否启用
			list.add(map);
		}
		
		hashMap.put("Rows",list);
		hashMap.put("Total", len);
		return JSON.toJSONString(hashMap);
	}
	@Override
	public Map<String, Object> getGuangbdShowType(int guangbdtype) {
		List<Guangbd> guangAdminbd = guangbd.getGuangShowbd(guangbdtype);
		Map<String,Object> map = new HashMap<String,Object>();
		int len = guangAdminbd.size();
		//url name visible note rating
		for(int i=0;i<len;i++){
			map.put(guangAdminbd.get(i).getGuangbdlocal(),guangAdminbd.get(i).getGuangbdcontext().replace("!-- ", "").replace(" --", ""));		//位置:广告内容
		}

		return map;
	}
	@Override
	public String saveupGuangbd(String datajsons) {
		//获得一个JSONArray数组
		JSONArray jsonArray = JSONArray.parseArray(datajsons);
		int L = jsonArray.size();
		
		//定义我们的Object
		Guangbd[] gbd = new Guangbd[L];
		Guangbd t = null;
		
		//for循环，将每一个对象集合封装在一个map中
		for(int i =0;i<L;i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if((jsonObject.getString("__status")).equals("update")){
				t = guangbd.getGuangbd(jsonObject.getIntValue("guangbdid"));
				t.setGuangbdlocal(jsonObject.getString("guangbdlocal"));
				t.setGuangbddes(jsonObject.getString("guangbddes"));
				t.setGuangbdtype(jsonObject.getIntValue("guangbdtype"));
				t.setGuangbdcontext(jsonObject.getString("guangbdcontext"));
				t.setGuangbdis(jsonObject.getIntValue("guangbdis"));
				gbd[i] = t;
			}else if((jsonObject.getString("__status")).equals("add")){
				t = new Guangbd();
				t.setGuangbdlocal(jsonObject.getString("guangbdlocal"));
				t.setGuangbddes(jsonObject.getString("guangbddes"));
				t.setGuangbdtype(jsonObject.getIntValue("guangbdtype"));
				t.setGuangbdcontext(jsonObject.getString("guangbdcontext"));
				t.setGuangbdis(jsonObject.getIntValue("guangbdis"));
				gbd[i] = t;
			}else{
				return null;
			}
		}
		return guangbd.saveOrUpdataGuangbd(gbd);
	}
	
	@Override
	public String delGuangbds(String guangbdids) {
		if(null==guangbdids||guangbdids.length()==0){
			
		}else{
			String[] gbdids = guangbdids.split(",");
			int len = gbdids.length;
			int[] gbdid = new int[len];
			for(int i=0;i<len;i++){
				gbdid[i]=Integer.parseInt(gbdids[i]);
			}
			return guangbd.deleteGuangbd(gbdid);
		}
		return "错误";
	}
	@Override
	public String saveSlide(HttpServletRequest request) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		String fileName="";
		//判断request中是否有multipartResolver类型数据，有就表示有文件类型
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			
			//获得文件名，这里是迭代器封装
			Iterator iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//这是前台name字段的名字，比如这里是filename
				String filename = (String) iter.next();

				//根据name字段来获得MultipartFile
				MultipartFile file = multiRequest.getFile(filename);
				
				//如果file不为空，就表示是一个MultipartFile文件
				//这里可不判断，因为上面已经判断了有文件类型
				if(file != null){
					fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm_ss").format(new Date())+"_slide_"+file.getOriginalFilename();
					String path =EnumPath.webinf.getClassPath()+EnumPath.updatepath.getClassPath()+fileName;

					File localFile = new File(path);
					
					//Springmvc 提供的写文件方法
					try {
						file.transferTo(localFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		String opname = "silde_"+request.getParameter("index");
		if("".equals(fileName)){
			if("delok".equals(doptions.deleteop(opname))){
				return "图片已被清空";
			}else{
				return " 失败";
			}
			
		}else{
			String opvalue=fileName+",,,"+request.getParameter("pictext")+",,,"+request.getParameter("picurl");
			if("setok".equals(doptions.saveupdateop(opname, opvalue))){
				return "操作成功";
			}else{
				return "操作失败";
			}
		}
	}
	@Override
	public Map<String, Map<String, String>> getIndexslide() {
		Map<String, Map<String, String>> hashMap = new HashMap<String, Map<String, String>>();
		Map<String, String> map = null;
		List<Options> list = doptions.dautooptionOpname("silde");
		if(null==list||list.size()==0){
			
		}else{
			for(Options o:list){
				map = new HashMap<String, String>();
				map.put("url", o.getOptionvalue().split(",,,")[0]);
				map.put("title", o.getOptionvalue().split(",,,")[1]);
				map.put("picurl", o.getOptionvalue().split(",,,")[2]);
				hashMap.put(o.getOptionname(), map);
			}
		}
		return hashMap;
	}
	@Override
	public Map<String, Map<String, String>> getLinkedlide() {
		Map<String, Map<String, String>> hashMap = getIndexslide();
		//得到序列，并重新排序
		int len = hashMap.size();
		int[] ss = new int[len];
		int tempss;
		int i =0;
		for(Map.Entry<String, Map<String, String>> m:hashMap.entrySet()){
			ss[i] =Integer.parseInt(m.getKey().split("_")[1]);
			i++;
		}
		
		for(int j=0;j<len-1;j++){
			for(int k=0;k<len-1-j;k++){
				if(ss[k]>ss[k+1]){
					tempss=ss[k+1];
					ss[k+1]=ss[k];
					ss[k]=tempss;
				}
			}
		}
		
		Map<String, Map<String, String>> LinkedMap = new LinkedHashMap<String, Map<String, String>>();
		for(int j=0;j<len;j++){
			LinkedMap.put(String.valueOf(ss[j]), hashMap.get("silde_"+ss[j]));
		}
		return LinkedMap;
	}
}
