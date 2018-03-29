package springMVC.NLoveB.Advicebean;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import springMVC.NLoveB.dao.iter.DaoUserPermissionsIter;
import springMVC.NLoveB.po.UserMeta;
import springMVC.NLoveB.po.Users;
import springMVC.NLoveB.service.iter.PasswordIter;
import springMVC.NLoveB.utils.emailsender;
/**
 * 
 * @ClassName:  AdviceLoginRegister   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:57:32   
 *   
 * @param <T>  
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Aspect
@Component("abviceCookie")
public class AdviceLoginRegister<T> {
	@Resource(name="daouserper")
	private DaoUserPermissionsIter duserper;
	@Resource(name="utilpassword")
	private PasswordIter password;
	
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLoginOrRegisteriter.slogin(..))",returning="returnValue")
	public void login(JoinPoint point, Object returnValue) {		
//      String operType = point.getSignature().getName();		//返回方法名
//      String entity = point.getTarget().getClass().getName();//返回class名
//      Object obj[] = point.getArgs();								//返回参数
		Map<String,Object> map =(Map<String, Object>) returnValue;
		if(map.get("loginok").equals("1")){
			duserper.changedstatus((int) map.get("userid"), 1);
		}
	}

	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLoginOrRegisteriter.slogout(..))",returning="returnValue")
	public void logout(JoinPoint point, Object returnValue) {		
//      String operType = point.getSignature().getName();		//返回方法名
//      String entity = point.getTarget().getClass().getName();//返回class名
//      Object obj[] = point.getArgs();								//返回参数
			duserper.changedstatus((int) returnValue, -1);
	}
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLoginOrRegisteriter.registeruser(..))",returning="returnValue")
	public void register(JoinPoint point, Object returnValue) {		
			Object obj[] = point.getArgs();
			String email = (String) obj[1];
			String pass = (String) returnValue;
			if(pass.equals("err")){
				System.out.println("错误，用户名或邮箱重复");
			}else{
				emailsender es = new emailsender();
				String[] emails={email};
				es.senderemail("用户您好，您的注册密码是"+pass+",您可以登录网站进行修改http://www.028888.net", emails);
			}
	}
	
	//找回密码
	@AfterReturning(pointcut="execution(* springMVC.NLoveB.service.iter.SerLoginOrRegisteriter.useroremail(..))",returning="returnValue")
	public void useremail(JoinPoint point, Object returnValue) {		
		String json = (String) returnValue;
		String strum = null;
		Users useridinfo = null;
		String value = "";
		Map<String, String> map = JSON.parseObject(json, new TypeReference<Map<String,String>>(){});
		if(map.get("msg").equals("lostok")){
			int userid = Integer.parseInt(map.get("userid"));
			useridinfo = duserper.useridinfo(userid);
			
			UserMeta um = new UserMeta();
			//新的keyvalue
			String key = "lostpass";
			//value = id 时间
			value = password.getSecretkey(userid);
			Users u1 = new Users();
			u1.setUserid(useridinfo.getUserid());
			um.setUmetakey(key);
			um.setUuserid(u1);
			
			//开始获得usermeta的集合
			Set<UserMeta> usermeta = useridinfo.getUsermeta();
			for(UserMeta set:usermeta){
				if(set.getUmetakey().equals(key)){
					um = set;
					break;
				}
			}
			/*
			 * 如果有lostpass 那么就更新他的值
			 * 如果没有，那么就表示um并没有被初始化 key u1 value都是自定义的
			 */
			um.setUmetavalue(value);
			
			strum = duserper.updateUser(um);
		}
		if(null==strum||strum.length()==0){
			System.out.println("更新用户的lostpass错误了");
		}else{
			//发送邮件
			String[] emails={useridinfo.getUseremail()};
			String text;

			try {
				text = "亲爱的用户您好，您现在正在找回密码，需要点击链接去修改密码<br />"
						+ "如果这不是您的操作，请忽略，找回密码链接将会在一天后失效<br />"
						+ "<a href='http://www.028888.net/user/login.html?action=lostpassword&emailkey="
								+ new String(Base64.encodeBase64(value.getBytes("utf-8")), "utf-8") + "'>修改密码</a>";
				emailsender es = new emailsender();
				es.senderemail(text,emails);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
