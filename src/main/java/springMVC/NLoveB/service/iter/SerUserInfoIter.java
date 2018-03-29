package springMVC.NLoveB.service.iter;

/**
 * 
 * @ClassName:  SerUserInfoIter   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:08:24   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public interface SerUserInfoIter {
	//更新用户信息
	public String opuserinfo(String userid,String displayname,String blogurl,String user_qq,String user_weixin,String user_weibo);
	//修改用户密码
	public String opuserpass(String userid,String oldpass,String newpass);
	//修改用户密码
	public String opuserpass(String newpass,String emailkey);
	
	//清除userlostpass 即修改密码的userkeyvalue
	public String cleanlostpass();

	//验证key里所包含的lostpass是否过期，不存在也表示过期
	public String valuserlostpass(String emailkey);
	//获得所有的用户
	/**
	 * 用户id 用户姓名 注册时间 用户邮件 用户昵称 网址 QQ 微信 微薄
	 * @param page
	 * @param pagesize
	 * @return
	 */
	String adminalluser(String page,String pagesize);
	
	/**
	 * @param iden 1 修改， -1 是删除
	 * @param jsons[{},{}]
	 * @return
	 */
	String upOperateUserInfo(String iden,String jsons);
}
