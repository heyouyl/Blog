package springMVC.NLoveB.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 * 分别实现预处理
 * 后处理（调用了Service并返回ModelAndView，但未进行页面渲染）
 * 返回处理（已经渲染了页面） 
 * 在preHandle中，可以进行编码、安全控制等处理； 
 * 在postHandle中，有机会修改ModelAndView； 
 * 在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录。
 */
/**
 * 
 * @ClassName:  init   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:04:54   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class init extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();

		if(request.getMethod().equalsIgnoreCase("GET")){
			request.setAttribute("sss", "dfdsfdsfds");
			response.sendRedirect(url.replace(".do", ".html"));
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
