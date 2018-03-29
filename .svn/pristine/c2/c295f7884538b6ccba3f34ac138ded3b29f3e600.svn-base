package springMVC.NLoveB.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @ClassName:  configProperties   
 * @Description:TODO(获得配置文件信息，封装到map中)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:18:26   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public final class configProperties {
	private static Map<String,String> map;
	private static Properties prop = new Properties();
	private static String web_path;
	
	private configProperties(){
	}
	
	public static void setProperties(String web_path){
		configProperties.web_path = web_path;
		
		//将数据存入到map和prop中
		setMap();
	}
	private static void setMap(){
		map = new HashMap<String, String>();
		//获得配置文件 
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(web_path+EnumPath.config_webinfo.getClassPath()));
			prop.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//迭代
		Iterator<String> it=prop.stringPropertyNames().iterator();
		//将信息保存到map中
		while(it.hasNext()){
			//获得配置文件的参数
			String params = it.next();
			//获得该参数的值
			String values = prop.getProperty(params);
			
			map.put(params, values);
		}
	}
	
	//获得全部的配置文件信息
	public static Map<String, String> getMap(){
		return map;
	}
	//获得具体的某一个配置信息，比如是否验证码
	public static String getProp(String name){
		return prop.getProperty(name);
	}
	
	//获得setinit的为map
	public static Map<String,String> setinitToMap(){
		Map<String,String> parseObject = JSON.parseObject(getProp("setinit"),Map.class);
		return parseObject;
	}
	//获得setinit的某个参数值
	public static String setinitToMap(String key){
		return setinitToMap().get(key);
	}
	//设置具体的某一个配置信息
	public static void setProp(String key,String value){
		prop.setProperty(key, value);
		updateprop(prop);
		 
	}
	//设置具体的某一个配置信息
	public static void setProp(Map<String,String> map){
		for(Map.Entry<String, String> entry : map.entrySet()){
			prop.setProperty(entry.getKey(), entry.getValue());
		}
		updateprop(prop);
	}
	
	
	private static void updateprop(Properties prop){
		OutputStream ops;
		try {
			ops = new FileOutputStream(web_path+EnumPath.config_webinfo.getClassPath());
			prop.store(ops, "Update 'key'");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
