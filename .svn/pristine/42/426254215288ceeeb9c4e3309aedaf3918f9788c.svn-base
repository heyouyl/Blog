package springMVC.NLoveB.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import springMVC.NLoveB.dao.iter.DaoUserPermissionsIter;
import springMVC.NLoveB.po.UserMeta;
import springMVC.NLoveB.po.Users;
import springMVC.NLoveB.service.iter.PasswordIter;
import springMVC.NLoveB.service.iter.SerLoginOrRegisteriter;
import springMVC.NLoveB.test.wordpass;
import springMVC.NLoveB.utils.configProperties;
/**
 * 
 * @ClassName:  SerLoginOrRegisterimpl   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:11:02   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Service("serloginregister")
public class SerLoginOrRegisterimpl implements SerLoginOrRegisteriter{
	@Resource(name="daouserper")
	private DaoUserPermissionsIter duserper;
	@Resource(name="utilpassword")
	private PasswordIter password;
	@Override
	public Map<String,Object> slogin(String username, String password) {
		Users user = new Users();
		user.setUsername(username);
		
		List<Users> list = duserper.dlogin(user);
		
		/*
		 * 根据用户名和密码判断该用户是否存在，返回匹配的数据数
		 * -1：没有这个用户
		 * 1：登录正确
		 * 0: 登录失败
		 * 2：其他错误
		 */
		//返回一个map吧
		Map<String,Object> map = new HashMap<String,Object>();
		if(null == list || list.size()==0){
			map.put("loginok", "-1");
		}else if(list.size()==1){
			user = (Users) list.get(0);
			if(user.getUserpass().equals(wordpass.WordpressEncrypt(password, user.getUserpass().substring(4, 12)))){
				System.out.println("aaaaa");
				map.put("loginok", "1");
				map.put("username", user.getUsername());			//获得用户名
				map.put("userid", user.getUserid());				//获得用户id
				map.put("useremail", user.getUseremail());				//获得用户id
				map.put("userdate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(user.getRegdate()));				//获得用户id
				map.put("displayname", user.getDisplayname());		//获得显示的用户名
				map.put("blogurl", user.getBlogurl());
				
				Iterator<UserMeta> iterator = user.getUsermeta().iterator();
				while(iterator.hasNext()){
					UserMeta next = iterator.next();
					String umetakey = next.getUmetakey();
					if(umetakey.equals("my028888_user_level")||umetakey.equals("my028888_user_qq")||umetakey.equals("my028888_user_weixin")||umetakey.equals("my028888_user_weibo")){
						map.put(umetakey.split("my028888_")[1], next.getUmetavalue());
					}
				}
			}else{
				map.put("loginok", "0");
			}
		}else{
			map.put("loginok", "2");
		}
		return map;
	}
	@Override
	public int slogout(Map<String, Object> userlog) {
		// TODO Auto-generated method stub
		return (int) userlog.get("userid");
	}
	
	@Override
	public String registeruser(String user_login, String user_email) {
		List<Object> useremail = duserper.useremail(user_login, user_email);
		if(null==useremail||useremail.size()==0){
			Users user = new Users();
			user.setUsername(user_login);
			user.setNicename(user_login);
			user.setDisplayname(user_login);
			user.setRegdate(new Date());
			user.setUseremail(user_email);
			//获得密码
			String pass = password.getPass(8);
			user.setUserpass(wordpass.WordpressEncrypt(pass, password.getPass(8)));
			
			//设置用户组
			Set<UserMeta> usermeta = new HashSet<UserMeta>();
			Map<String,String> map = JSON.parseObject(configProperties.getProp("setinit"), Map.class);
			UserMeta um = new UserMeta();
			um.setUuserid(user);
			um.setUmetakey("my028888_user_level");
			um.setUmetavalue(map.get("web_newusergroup"));
			usermeta.add(um);
			user.setUsermeta(usermeta);
			
			duserper.updateUser(user);
			return pass;
		}else{
			return "err";
		}
	}
	@Override
	public String useroremail(String iden,String lostpass) {
		Map<String,String> map = new HashMap<String,String>();
		List<Users> list=null;
		System.out.println(iden);
		if(iden.equals("email")){
			list = duserper.useremail(lostpass);
		}else{
			list = duserper.username(lostpass);
		}
		
		if(null == list || list.size()==0){
			map.put("msg", "losterr");
		}else{
			Users users = list.get(0);
			String username = users.getUsername();
			username = username.charAt(0)+"******"+username.charAt(username.length()-1);
			String useremail = users.getUseremail();
			useremail = useremail.charAt(0)+"***@***"+useremail.charAt(username.length()-1);
			String idens = (iden.equals("email")?username:useremail);
			map.put("msg", "lostok");
			map.put("iden", idens);
			map.put("userid", String.valueOf(users.getUserid()));
		}
		// TODO Auto-generated method stub
		return JSON.toJSONString(map);
	}
	@Override
	public String userResetPass(String lostpassuid, String newpass) {
		String updatePassword = duserper.updatePassword(Integer.parseInt(lostpassuid),wordpass.WordpressEncrypt(newpass, password.getPass(8)));  //uppassok
		return updatePassword;
	}

}
