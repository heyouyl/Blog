package springMVC.NLoveB.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * @ClassName:  Comments   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:05:57   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="comments")
@Table(name="blog_comments")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class Comments {
/*
 * CREATE TABLE IF NOT EXISTS `my028888_comments` (
  `comment_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `comment_post_ID` bigint(20) unsigned NOT NULL DEFAULT '0',
  `comment_author` tinytext NOT NULL,
  `comment_author_email` varchar(100) NOT NULL DEFAULT '',
  `comment_author_url` varchar(200) NOT NULL DEFAULT '',
  `comment_author_IP` varchar(100) NOT NULL DEFAULT '',
  `comment_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_date_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `comment_content` text NOT NULL,
  `comment_karma` int(11) NOT NULL DEFAULT '0',
  `comment_approved` varchar(20) NOT NULL DEFAULT '1',
  `comment_agent` varchar(255) NOT NULL DEFAULT '',
  `comment_type` varchar(20) NOT NULL DEFAULT '',
  `comment_parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `user_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `comment_mail_notify` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_ID`),
  KEY `comment_post_ID` (`comment_post_ID`),
  KEY `comment_approved_date_gmt` (`comment_approved`,`comment_date_gmt`),
  KEY `comment_date_gmt` (`comment_date_gmt`),
  KEY `comment_parent` (`comment_parent`),
  KEY `comment_author_email` (`comment_author_email`(10))
 */
	private int commentid;				//自动
	private int commentpostid;			//	前台
	private String commentauthor;		//	前台
	private String commenetauthoremail;	//	前台
	private String commentauthorurl;	//前台
	private String commentauthorip;		//后台
	private Date commenetdate;			//后台
	private String commenetcontent;		//	前台
	private int commenetkarma;			//空（后台）
	private String commenetapproved;	//后台（0）
	private String commenetagent;		//浏览器信息前台
	private String commenettype;		//空（后台）
	private int commenetparent;			//前台 夫评论
	private int commenetuserid;			//	前台
	private int commenetmailnotify;		//后台
	
	@Id
	@Column(name = "comment_ID", unique = true, nullable = false,length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCommentid() {
		return commentid;
	}
	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}
	
	@Column(name = "comment_post_ID", unique = false, nullable = false,length=20)
	public int getCommentpostid() {
		return commentpostid;
	}
	public void setCommentpostid(int commentpostid) {
		this.commentpostid = commentpostid;
	}

	@Column(name = "comment_author", unique = false,nullable = false,columnDefinition="tinytext NOT NULL")
	public String getCommentauthor() {
		return commentauthor;
	}
	public void setCommentauthor(String commentauthor) {
		this.commentauthor = commentauthor;
	}
	
	@Column(name = "comment_author_email", unique = false,nullable = false,length=100)
	public String getCommenetauthoremail() {
		return commenetauthoremail;
	}
	public void setCommenetauthoremail(String commenetauthoremail) {
		this.commenetauthoremail = commenetauthoremail;
	}

	@Column(name = "comment_author_url", unique = false,nullable = true,length=200)
	public String getCommentauthorurl() {
		return commentauthorurl;
	}
	public void setCommentauthorurl(String commentauthorurl) {
		this.commentauthorurl = commentauthorurl;
	}
	
	@Column(name = "comment_author_IP", unique = false,nullable = true,length=100)
	public String getCommentauthorip() {
		return commentauthorip;
	}
	public void setCommentauthorip(String commentauthorip) {
		this.commentauthorip = commentauthorip;
	}
	
	@Column(name = "comment_date", unique = false, nullable = false,columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	public Date getCommenetdate() {
		return commenetdate;
	}
	public void setCommenetdate(Date commenetdate) {
		this.commenetdate = commenetdate;
	}
	public void setCommenetdate(String commenetdate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.commenetdate = format.parse(commenetdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Column(name = "comment_content", unique = false, nullable = false,columnDefinition="text NOT NULL")
	public String getCommenetcontent() {
		return commenetcontent;
	}
	public void setCommenetcontent(String commenetcontent) {
		this.commenetcontent = commenetcontent;
	}

	@Column(name = "comment_karma", unique = false,nullable = true,columnDefinition="int(11) NOT NULL DEFAULT '0'")
	public int getCommenetkarma() {
		return commenetkarma;
	}
	public void setCommenetkarma(int commenetkarma) {
		this.commenetkarma = commenetkarma;
	}

	@Column(name = "comment_approved", unique = false,nullable = true,columnDefinition="varchar(20) NOT NULL DEFAULT '0'")
	public String getCommenetapproved() {
		return commenetapproved;
	}
	public void setCommenetapproved(String commenetapproved) {
		this.commenetapproved = commenetapproved;
	}
	
	@Column(name = "comment_agent", unique = false,nullable = false,length=255)
	public String getCommenetagent() {
		return commenetagent;
	}
	public void setCommenetagent(String commenetagent) {
		this.commenetagent = commenetagent;
	}
	
	@Column(name = "comment_type", unique = false,nullable = false,length=20)
	public String getCommenettype() {
		return commenettype;
	}
	public void setCommenettype(String commenettype) {
		this.commenettype = commenettype;
	}
	
	@Column(name = "comment_parent", unique = false,nullable = true,columnDefinition="bigint(20) unsigned NOT NULL DEFAULT '0'")
	public int getCommenetparent() {
		return commenetparent;
	}
	public void setCommenetparent(int commenetparent) {
		this.commenetparent = commenetparent;
	}

	@Column(name = "user_id", unique = false,nullable = true,columnDefinition="bigint(20) unsigned NOT NULL DEFAULT '0'")
	public int getCommenetuserid() {
		return commenetuserid;
	}
	public void setCommenetuserid(int commenetuserid) {
		this.commenetuserid = commenetuserid;
	}

	@Column(name = "comment_mail_notify", unique = false,nullable = true,columnDefinition="tinyint(4) NOT NULL DEFAULT '0'")
	public int getCommenetmailnotify() {
		return commenetmailnotify;
	}
	public void setCommenetmailnotify(int commenetmailnotify) {
		this.commenetmailnotify = commenetmailnotify;
	}
}
