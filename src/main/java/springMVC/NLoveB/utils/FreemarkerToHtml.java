package springMVC.NLoveB.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * 
 * @ClassName:  FreemarkerToHtml   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:17:02   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class FreemarkerToHtml {
	private static FreeMarkerConfigurer freeMarkerConfigurer;
	
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		FreemarkerToHtml.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		return freeMarkerConfigurer;
	}

	/*
	 * 使用 FreemarkerToHtml.process("/article.ftl",yyyy+"_"+MM+"_"+arid, map);
	 */
	public static void process(String templateName,String saveName, Map<String,Object> map) throws Exception {
		Configuration configuration = freeMarkerConfigurer.getConfiguration();
		
		Template template = configuration.getTemplate(templateName);
		File file = new File(EnumPath.webinf.getClassPath()+EnumPath.arhtmlpath.getClassPath()+saveName+".ftl");

		Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		template.process(map, out);
		IOUtils.closeQuietly(out);
	}
}
