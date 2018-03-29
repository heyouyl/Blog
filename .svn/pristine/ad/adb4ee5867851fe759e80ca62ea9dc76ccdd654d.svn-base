package springMVC.NLoveB.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 
 * @ClassName:  Commentmeta   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:05:49   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="commentmeta")
@Table(name="blog_commentmeta")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class Commentmeta {
/*
 * CREATE TABLE IF NOT EXISTS `my028888_commentmeta` (
  `meta_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `meta_key` varchar(255) DEFAULT NULL,
  `meta_value` longtext,
  PRIMARY KEY (`meta_id`),
  KEY `comment_id` (`comment_id`),
  KEY `meta_key` (`meta_key`(191))
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1451 ;
 */
	
	private int metaid;
	private int commentid;
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
	
	@Column(name = "comment_id", unique = false, nullable = true,columnDefinition="bigint(20) unsigned NOT NULL DEFAULT '0'")
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	
	@Column(name = "meta_key", unique = false, nullable = true)
	public String getMetakey() {
		return metakey;
	}
	public void setMetakey(String metakey) {
		this.metakey = metakey;
	}
	
	@Column(name = "meta_value", unique = false, nullable = true,columnDefinition="longtext")
	public String getMetavalue() {
		return metavalue;
	}
	public void setMetavalue(String metavalue) {
		this.metavalue = metavalue;
	}
	
	
}
