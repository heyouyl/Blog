package springMVC.NLoveB.service.iter;
/**
 * 
 * @ClassName:  KeymishiIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:09:38   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface KeymishiIter {
	//注册时 发送的邮件验证信息key
	public String getEamilkey(String username);		//通过用户名进行加密
	public String getJEamilkey(String emailkey);	//通过key解密
	
	//登录时对于cookie中保存用户信息的加密 cookie(rememberme)
	public String getJamkey(String arg);		//参数是需要加密的信息 返回加密字符串
	public String getJemkey(String key);		//参数是加密后的信息 返回解密字符串
}
