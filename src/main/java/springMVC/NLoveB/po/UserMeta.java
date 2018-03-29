package springMVC.NLoveB.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @ClassName:  UserMeta   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:26   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="usermeta")
@Table(name="blog_usermeta")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class UserMeta {
	/*
	 * CREATE TABLE IF NOT EXISTS `my028888_usermeta` (
  `umeta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`umeta_id`),
  KEY `user_id` (`user_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=169 ;
	 */
	
	private int umetaid;
	private Users uuserid;
	private String umetakey;
	private String umetavalue;
	
	@Id
	@Column(name = "umeta_id", unique = true, nullable = false,length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUmetaid() {
		return umetaid;
	}
	public void setUmetaid(int umetaid) {
		this.umetaid = umetaid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id",unique = false, nullable = true,columnDefinition="bigint(20) unsigned NOT NULL DEFAULT '0'")
	public Users getUuserid() {
		return uuserid;
	}
	public void setUuserid(Users uuserid) {
		this.uuserid = uuserid;
	}
	
	@Column(name = "meta_key", unique = false, nullable = true,length=255)
	public String getUmetakey() {
		return umetakey;
	}
	public void setUmetakey(String umetakey) {
		this.umetakey = umetakey;
	}
	
	@Column(name = "meta_value", unique = false, nullable = true,columnDefinition="LONGTEXT NOT NULL")
	public String getUmetavalue() {
		return umetavalue;
	}
	public void setUmetavalue(String umetavalue) {
		this.umetavalue = umetavalue;
	}
	
	

}
