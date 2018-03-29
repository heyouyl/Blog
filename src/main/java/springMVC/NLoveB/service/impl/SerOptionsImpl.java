package springMVC.NLoveB.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import springMVC.NLoveB.dao.iter.DaoOptionsIter;
import springMVC.NLoveB.po.Options;
import springMVC.NLoveB.service.iter.SerOptionsIter;
/**
 * 
 * @ClassName:  SerOptionsImpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:10:57   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("seroptions")
public class SerOptionsImpl implements SerOptionsIter {
	@Resource(name="daooptions")
	private DaoOptionsIter<Options> doptions; 
	@Override
	public Map<String,String> getOptionsAuto() {
		//假设获得了所有的自动加载的数据
		List<Options> list = doptions.dautooption();
		int len = list.size();
		String[] key = new String[len];
		String[] value = new String[len];
		Map<String,String> maps = new HashMap<String,String>();
		for(int i=0;i<len;i++){
			Options op = list.get(i);
			maps.put(op.getOptionname(), op.getOptionvalue());
		}
		return maps;
	}

}
