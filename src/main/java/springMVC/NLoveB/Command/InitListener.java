package springMVC.NLoveB.Command;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import springMVC.NLoveB.service.iter.SerArticle_PostsIter;
import springMVC.NLoveB.service.iter.SerOptionsIter;
import springMVC.NLoveB.utils.configProperties;

/**
 * 
 * @ClassName:  InitListener   
 * @Description:TODO(初始化信息)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午10:58:43   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent>{
	@Resource(name="serarposts")
	private SerArticle_PostsIter serarposts;
	@Resource(name="seroptions")
	private SerOptionsIter seroptions;
	
	private ServletContext servletContext;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		Map<String,String> optionsAuto = seroptions.getOptionsAuto();			//获得自动加载的数据，是一个list<String[]>
		optionsAuto.put("arcount", String.valueOf(serarposts.getcount()));		//获得总的文章数据
		String web_path=this.servletContext.getRealPath("");					//获得项目的物理路劲
		optionsAuto.put("web_path", web_path);
		optionsAuto.put("obj_name", this.servletContext.getContextPath());		//获得项目的名称
		
		optionsAuto.put("pagination", "show");									//显示分页
		
		//将以上数据存入配置文件
		//初始化configProperties
		configProperties.setProperties(web_path); 
		configProperties.setProp(optionsAuto);
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;	
	}
}
