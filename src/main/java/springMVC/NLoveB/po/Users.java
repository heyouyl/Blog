package springMVC.NLoveB.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @ClassName:  Users   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:19   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="users")
@Table(name="blog_users")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class Users {
	/*
	 * CREATE TABLE IF NOT EXISTS `my028888_users` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_login` varchar(60) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_nicename` varchar(50) NOT NULL DEFAULT '',
  `user_email` varchar(100) NOT NULL DEFAULT '',
  `user_url` varchar(100) NOT NULL DEFAULT '',
  `user_registered` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `user_activation_key` varchar(255) NOT NULL DEFAULT '',
  `user_status` int(11) NOT NULL DEFAULT '0',
  `display_name` varchar(250) NOT NULL DEFAULT '',
  PRIMARY KEY (`ID`),
  KEY `user_login_key` (`user_login`),
  KEY `user_nicename` (`user_nicename`),
  KEY `user_email` (`user_email`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;
	 */
	private int userid;			//用户id
	private String username;	//用户名
	private String userpass;	//用户密码
	private String nicename;	//姓名
	private String useremail;	//email
	private String blogurl;		//blogurl
	private Date regdate;		//注册时间
	private String activation;	//激活的设备
	private int status;			//登陆状态
	private String displayname;//显示的用户名
	
	private Set<UserMeta> usermeta = new HashSet<UserMeta>();
	
	@Id
	@Column(name = "ID", unique = true, nullable = false,length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Column(name = "user_login", unique = true, nullable = false,length=60)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "user_pass", unique = false, nullable = false,length=255)
	public String getUserpass() {
		return userpass;
	}
	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
	@Column(name = "user_nicename", unique = false, nullable = true,length=50)
	public String getNicename() {
		return nicename;
	}
	public void setNicename(String nicename) {
		this.nicename = nicename;
	}
	
	@Column(name = "user_email", unique = false, nullable = false,length=100)
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	
	@Column(name = "user_url", unique = false, nullable = true,length=100)
	public String getBlogurl() {
		return blogurl;
	}
	public void setBlogurl(String blogurl) {
		this.blogurl = blogurl;
	}
	
	@Column(name = "user_registered", unique = false, nullable = false,length=100)
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Column(name = "user_activation_key", unique = false, nullable = true,length=255)
	public String getActivation() {
		return activation;
	}
	public void setActivation(String activation) {
		this.activation = activation;
	}
	
	@Column(name = "user_status", unique = false, nullable = true,columnDefinition="int(11) NOT NULL DEFAULT '0'")
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Column(name = "display_name", unique = false, nullable = true,length=255)
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "uuserid")
	public Set<UserMeta> getUsermeta() {
		return usermeta;
	}
	public void setUsermeta(Set<UserMeta> usermeta) {
		this.usermeta = usermeta;
	}

}
