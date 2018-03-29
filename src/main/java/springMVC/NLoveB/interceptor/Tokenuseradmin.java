package springMVC.NLoveB.interceptor;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  Tokenuseradmin   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:04:31   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class Tokenuseradmin extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException{	
		//获得session
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1800);

		if(null == session.getAttribute("userlogin")){
			response.sendRedirect(configProperties.getProp("obj_name")+"/user/login.html");	//如果没有登录session 就返回登录页面
		}
		return true;
	}
}
