package springMVC.NLoveB.service.iter;

import java.util.Map;

/**
 * 
 * @ClassName:  SerLoginOrRegisteriter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:08:47   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerLoginOrRegisteriter {
	public Map<String,Object> slogin(String username,String password);
	public int slogout(Map<String,Object> userlog);
	public String registeruser(String user_login,String user_email);	//快速注册
	public String useroremail(String iden,String lostpass);	//根据用户名或邮箱找回密码
	/**
	 * 重置用户密码
	 * @param lostpassuid 用户id
	 * @param newpass 用户密码
	 * @return
	 */
	public String userResetPass(String lostpassuid,String newpass);	//根据用户名或邮箱找回密码
}
