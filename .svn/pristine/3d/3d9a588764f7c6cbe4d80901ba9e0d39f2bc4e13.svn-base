package springMVC.NLoveB.po;

import javax.persistence.CascadeType;
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
 * @ClassName:  Postmeta   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:35   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="postmeta")
@Table(name="blog_postmeta")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class Postmeta {
	/*
	 * CREATE TABLE IF NOT EXISTS `my028888_postmeta` (
  `meta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`meta_id`),
  KEY `post_id` (`post_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5104 ;
	 */

	private int metaid;
	private Posts postid;
	private String metakey;
	private String metavalue;
	
	@Id
	@Column(name = "meta_id", unique = true, nullable = false,length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getMetaid() {
		return metaid;
	}
	public void setMetaid(int metaid) {
		this.metaid = metaid;
	}
	
	@Column(name = "meta_key", unique = false, nullable = false,length=255)
	public String getMetakey() {
		return metakey;
	}
	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}
	
	@Column(name = "meta_value", unique = false, nullable = false,columnDefinition="LONGTEXT NOT NULL")
	public String getMetavalue() {
		return metavalue;
	}
	public void setMetavalue(String metavalue) {
		this.metavalue = metavalue;
	}
	
	
	//延迟加载：多对一方式
	//关联信息：外键name = "post_id" 急加载
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id",unique = false, nullable = false)
	public Posts getPostid() {
		return postid;
	}
	public void setPostid(Posts postid) {
		this.postid = postid;
	}
	
}
