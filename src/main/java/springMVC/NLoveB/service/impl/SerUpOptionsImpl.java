package springMVC.NLoveB.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import springMVC.NLoveB.dao.iter.DaoOptionsIter;
import springMVC.NLoveB.po.Options;
import springMVC.NLoveB.service.iter.SerUpOptionsIter;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  SerUpOptionsImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:10:45   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serupoptions")
public class SerUpOptionsImpl implements SerUpOptionsIter {
	@Resource(name="daooptions")
	private DaoOptionsIter<Options> doptions;
	
	@Override
	public String upoptionsinit(String data) {
		String prop = configProperties.getProp("setinit");		//获得配置文件中的基本信息
		if(null==prop||prop.length()==0|| !prop.equals(data)){
			//配置文件不存在或他们不相等，就执行更新数据库和配置文件
			configProperties.setProp("setinit", data);
			//JSON.parseObject(data, new TypeReference<goodsfl[]>(){};
			Map<String,String> parseObject = JSON.parseObject(data, Map.class);
			if(null==parseObject.get("_txt_val")||parseObject.get("txt_val").length()==0){
			}else{
				parseObject.remove("_txt_val");
			}
			configProperties.setProp(parseObject);
		}
		doptions.saveupdateop("setinit", data);
		return "upok";
	}

}
