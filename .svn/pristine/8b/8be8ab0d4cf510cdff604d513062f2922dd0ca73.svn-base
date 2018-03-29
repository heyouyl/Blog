package springMVC.NLoveB.service.iter;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
/**
 * 
 * @ClassName:  PasswordIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:10:08   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface PasswordIter {
	public String getPass(int count);
	public String getSecretkey(int userid);
	public Map<String,Object> getDecryptKey(String key) throws ParseException;
	//判断时间是否过期（验证email链接是否过期，参数是emailvalue）
	public boolean overdate(Date starttime);
}
