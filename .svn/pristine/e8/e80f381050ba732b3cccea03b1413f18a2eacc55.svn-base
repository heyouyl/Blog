package springMVC.NLoveB.utils;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 
 * @ClassName:  emailsender   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:16:22   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
public class emailsender {
	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	private Properties javaMailProperties = new Properties();
	private MimeMessageHelper help;
	private MimeMessage message;
	
	private static Map map= new HashMap();
	//创建会话对象
	static{
		Properties prop = new Properties();
		try{
//			System.out.println("sasas");
			//读取属性文件a.properties
			//InputStream in = new BufferedInputStream (new FileInputStream("src/email.properties")); 应用程序指定
			//InputStream in = new BufferedInputStream (new FileInputStream(new File("").getAbsolutePath()+"\\workspace\\Enterprise_message_board\\build\\classes\\email.properties"));
			//以下方法来自：http://blog.csdn.net/friendan/article/details/19839767
			InputStream in = new BufferedInputStream(new FileInputStream(EnumPath.Eamil.getClassPath()));
			//下为linux
			//InputStream in = new BufferedInputStream(new FileInputStream(Thread.currentThread().getContextClassLoader().getResource("").toString().replace("file:", "")+"email.properties"));
			//加载属性列表
			prop.load(in);
			//map = prop;
			Iterator<String> it=prop.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				map.put(key,prop.getProperty(key));
				//System.out.println(key+":"+map.get(key));
			}
			
			
			in.close();
			
	//		//这里随便记下 保存（写入）属性到b.properties文件
	//		FileOutputStream fos = new FileOutputStream("b.properties", true);//true表示追加打开
	//		prop.setProperty("phone", "10086");
	//		prop.store(fos, "The New properties file");
	//		fos.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	//初始化发送信息以及服务器参数
	public emailsender(){
		this.mailSender.setHost((String) map.get("host"));  
		this.mailSender.setPort(Integer.parseInt((String) map.get("port")));  
		this.mailSender.setUsername((String) map.get("username"));  
		this.mailSender.setPassword((String) map.get("password"));
		System.out.println(map.get("mail.smtp.auth"));
		this.javaMailProperties.put("mail.smtp.auth", map.get("mail.smtp.auth"));
		this.javaMailProperties.put("mail.smtp.starttls.enable",map.get("mail.smtp.starttls.enable"));
		//将参数properties，加入到初始化信息中
		this.mailSender.setJavaMailProperties(this.javaMailProperties);  
        //创建会话对象
        this.message = this.mailSender.createMimeMessage();  
        try {
			this.help = new MimeMessageHelper(this.message, true, "UTF-8");
			this.help.setFrom((String)map.get("fromer")); 
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //设置发送者邮箱
	}
	public void senderemail(String content,String[] email){      
        //需要发送的邮箱号码数据    
        try {
        	//发送给谁
			this.help.setTo(email);
			//发送的主题
	        this.help.setSubject("java爱好者会员邮件");   
	        //发送的主题
	        this.help.setText(content, true);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println("正在发送邮件，请稍后！。。。。。。");
        mailSender.send(message);
        System.out.println("邮件发送成功");
	}
}
