package springMVC.NLoveB.utils;
/**
 * 
 * @ClassName:  EnumPath   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:16:11   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public enum EnumPath {
	spot(path_spot()),		//对点的分割  win: /。  linux:\\。
	config_webinfo(path_config_webinfo()),		//获得配置文件
	loginregister_log(path_loginregister_log()),		//获得日志文件路径
	Eamil(path_email()),		//获得email的参数
	webinf(path_webinf()),		//获得web-inf地址
	classpath(path_class()),		//获得classpath地址
	arhtmlpath(path_arhtml()),		//获得html地址
	updatepath(path_update());		//上传的图片地址,相对于web-inf的目录地址
	
	private String path;
	
	private EnumPath(String path){
		this.path = path;
	}
	
	private static String path_webinf() {
		if(System.getProperty("os.name").toLowerCase().indexOf("win")>=0){
			return EnumClassPath.WinWebInf.getClassPath();
		}else{
			return EnumClassPath.LinuxWebInf.getClassPath();
		}
	}
	private static String path_class() {
		if(System.getProperty("os.name").toLowerCase().indexOf("win")>=0){
			return EnumClassPath.WinClassPath.getClassPath();
		}else{
			return EnumClassPath.LinuxClassPath.getClassPath();
		}
	}
	private static String path_update() {
		if(System.getProperty("os.name").indexOf("win")>=0){
			//这里获得配置文件的路径，我这里先直接写
			return "\\upload\\images\\";
		}else{
			return "/upload/images/";
		}
	}

	private static String path_spot() {
		if(System.getProperty("os.name").equals("win")){
			return "/.";
		}else{
			return "\\.";
		}
	}

	private static String path_email() {
		if(System.getProperty("os.name").equals("win")){
			return EnumClassPath.WinClassPath.getClassPath()+"email.properties";
		}else{
			return EnumClassPath.LinuxClassPath.getClassPath()+"email.properties";
		}
	}

	private static String path_config_webinfo(){
		if(System.getProperty("os.name").equals("win")){
			return "WEB-INF\\classes\\initweb\\config_webinfo.properties";
		}else{
			return "WEB-INF/classes/initweb/config_webinfo.properties";
		}	
	}
	private static String path_arhtml(){
		if(System.getProperty("os.name").equals("win")){
			return "\\theme\\freemaker\\html\\article\\";
		}else{
			return "/theme/freemaker/html/article/";
		}	
	}
	
	private static String path_loginregister_log(){
		if(System.getProperty("os.name").equals("win")){
			return EnumClassPath.WinWebInf.getClassPath()+"loginregister\\log";
		}else{
			return EnumClassPath.LinuxWebInf.getClassPath()+"loginregister/log";
		}
		
	}
	
	
	
	public String getClassPath() {  
        return this.path;  
    }
}
