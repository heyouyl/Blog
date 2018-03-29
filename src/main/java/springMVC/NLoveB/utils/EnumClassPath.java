package springMVC.NLoveB.utils;

/**
 * 
 * @ClassName:  EnumClassPath   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:15:07   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public enum EnumClassPath {
	/*
	 * windows 获得classpath路径，后面加"resources\\initweb\\config_webinfo.properties"
	 * windows 获得web-inf 路径
	 */
	WinClassPath(Thread.currentThread().getContextClassLoader().getResource("").toString().replace('/', '\\').replace("file:", "").replace("%20", " ").substring(1).replace("%20", " ")),
	WinWebInf(Thread.currentThread().getContextClassLoader().getResource("").toString().replace('/', '\\').replace("file:", "").replace("classes\\", "").replace("%20", " ").substring(1).replace("%20", " ")),
	/*
	 * linux 获得classpath路径，后面加"resources\\initweb\\config_webinfo.properties"
	 * linux 获得web-inf 路径
	 */
	LinuxClassPath(Thread.currentThread().getContextClassLoader().getResource("").toString().replace("file:", "").replace("%20", " ")),
	LinuxWebInf(Thread.currentThread().getContextClassLoader().getResource("").toString().replace("file:", "").replace("classes/", "").replace("%20", " "));
	

	private String classpath;
	private EnumClassPath(String classpath){
		this.classpath = classpath;
	}

	public String getClassPath() {  
        return this.classpath;  
    }
}
