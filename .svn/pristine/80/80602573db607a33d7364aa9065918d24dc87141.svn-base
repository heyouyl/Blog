package springMVC.NLoveB.po;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/*
 * 文章信息  
 */
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
 * @ClassName:  Posts   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:39   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="posts")
@Table(name="blog_posts")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class Posts {
	private int poid;					//文章id
	private int userid;				//用户id
	private Date podate;				//发表文章的时间
	private String pocontent;		//文章的正文
	private String potitle;			//文章的标题
	private String excerpt;			//文章的摘要
	private String postatus;		//文章的状态
	private String pocommentstatus;	//文章评论状态
	private String popass;			//文章的密码
	private String poname;			//文章的缩缕名
	private Date pomodified;		//修改文章的时间
	private int postparent;			//父文章
	private String guid;			//附件的地址，如图片，rar等
	private int pomenu_order;	//排序，如菜单
	private String post_type;	//post page attachment
	private String post_mime_type;//指定是application/rar image/png
	private int pocomment_count;	//评论总数
	
	//文章与文章的元数据 onetomany
	private Set<Postmeta> postmeta = new HashSet<Postmeta>(0);
	private Set<Term_relationships> term_relationships = new HashSet<Term_relationships>(0);
	/*
	 * CREATE TABLE IF NOT EXISTS `my028888_posts` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `post_author` bigint(20) unsigned NOT NULL DEFAULT '0',
  `post_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_date_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_content` longtext NOT NULL,
  `post_title` text NOT NULL,
  `post_excerpt` text NOT NULL,
  `post_status` varchar(20) NOT NULL DEFAULT 'publish',
  `comment_status` varchar(20) NOT NULL DEFAULT 'open',
  `ping_status` varchar(20) NOT NULL DEFAULT 'open',
  `post_password` varchar(20) NOT NULL DEFAULT '',
  `post_name` varchar(200) NOT NULL DEFAULT '',
  `to_ping` text NOT NULL,
  `pinged` text NOT NULL,
  `post_modified` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_modified_gmt` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `post_content_filtered` longtext NOT NULL,
  `post_parent` bigint(20) unsigned NOT NULL DEFAULT '0',
  `guid` varchar(255) NOT NULL DEFAULT '',
  `menu_order` int(11) NOT NULL DEFAULT '0',
  `post_type` varchar(20) NOT NULL DEFAULT 'post',
  `post_mime_type` varchar(100) NOT NULL DEFAULT '',
  `comment_count` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `type_status_date` (`post_type`,`post_status`,`post_date`,`ID`),
  KEY `post_parent` (`post_parent`),
  KEY `post_author` (`post_author`),
  KEY `post_name` (`post_name`(191))
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1650 ;
	 */
	/*
	 * 文章状态
	 * 	1、pending：待审
		2、draft：草稿
		3、auto-draft：自动保存的草稿
		4、inherit：修订版本
		5、trash：回收站
		6、publish：已发布
		7、future：定时
		8、private：私有
	 */
	@Id
	@Column(name = "ID", unique = true, nullable = false,length=20)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	
	@Column(name = "post_author", unique = false, nullable = false,length=20)
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Column(name = "post_date", unique = false, nullable = true,columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	public Date getPodate() {
		return podate;
	}
	public void setPodate(Date podate) {
		this.podate = podate;
	}
	public void setPodate(String podate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.podate = format.parse(podate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Column(name = "post_content", unique = false, nullable = true,columnDefinition="longtext NOT NULL")
	public String getPocontent() {
		return pocontent;
	}
	public void setPocontent(String pocontent) {
		this.pocontent = pocontent;
	}
	
	@Column(name = "post_title", unique = false, nullable = true,columnDefinition="text NOT NULL")
	public String getPotitle() {
		return potitle;
	}
	public void setPotitle(String potitle) {
		this.potitle = potitle;
	}
	
	@Column(name = "post_excerpt", unique = false, nullable = true,columnDefinition="text NOT NULL")
	public String getExcerpt() {
		return excerpt;
	}
	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}
	
	@Column(name = "post_status", unique = false, nullable = true,columnDefinition="varchar(20) NOT NULL DEFAULT 'publish'")
	public String getPostatus() {
		return postatus;
	}
	public void setPostatus(String postatus) {
		this.postatus = postatus;
	}
	
	@Column(name = "comment_status", unique = false, nullable = true,columnDefinition="varchar(20) NOT NULL DEFAULT 'open'")
	public String getPocommentstatus() {
		return pocommentstatus;
	}
	public void setPocommentstatus(String pocommentstatus) {
		this.pocommentstatus = pocommentstatus;
	}
	@Column(name = "post_password", unique = false, nullable = true,length=20)
	public String getPopass() {
		return popass;
	}
	public void setPopass(String popass) {
		this.popass = popass;
	}
	
	@Column(name = "post_name", unique = false, nullable = true,length=200)
	public String getPoname() {
		return poname;
	}
	public void setPoname(String poname) {
		this.poname = poname;
	}
	
	@Column(name = "post_modified", unique = false, nullable = true,columnDefinition="datetime NOT NULL DEFAULT '0000-00-00 00:00:00'")
	public Date getPomodified() {
		return pomodified;
	}
	public void setPomodified(Date pomodified) {
		this.pomodified = pomodified;
	}
	public void setPomodified(String pomodified){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.pomodified = format.parse(pomodified);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Column(name = "menu_order", unique = false, nullable = true,columnDefinition="int(11) NOT NULL DEFAULT '0'")
	public int getPomenu_order() {
		return pomenu_order;
	}
	public void setPomenu_order(int pomenu_order) {
		this.pomenu_order = pomenu_order;
	}
	
	@Column(name = "comment_count", unique = false, nullable = true,columnDefinition="bigint(20) NOT NULL DEFAULT '0'")
	public int getPocomment_count() {
		return pocomment_count;
	}
	public void setPocomment_count(int pocomment_count) {
		this.pocomment_count = pocomment_count;
	}

	@Column(name = "post_parent", unique = false, nullable = true,columnDefinition="bigint(20) unsigned NOT NULL DEFAULT '0'")
	public int getPostparent() {
		return postparent;
	}
	public void setPostparent(int postparent) {
		this.postparent = postparent;
	}

	@Column(name = "guid", unique = false, nullable = false,length=255)
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	//
	@Column(name = "post_type", unique = false, nullable = true,columnDefinition="varchar(20) NOT NULL DEFAULT 'post'")
	public String getPost_type() {
		return post_type;
	}
	public void setPost_type(String post_type) {
		this.post_type = post_type;
	}
	
	@Column(name = "post_mime_type", unique = false, nullable = true,length=20)
	public String getPost_mime_type() {
		return post_mime_type;
	}
	public void setPost_mime_type(String post_mime_type) {
		this.post_mime_type = post_mime_type;
	}
	
	
	//级联操作：cascade = CascadeType.ALL
	//延迟加载：fetch = FetchType.LAZY  (ENGER 急加载)
	//映射：mappedBy = "category"
	//一对多方式
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "postid")
	public Set<Postmeta> getPostmeta() {
		return postmeta;
	}
	public void setPostmeta(Set<Postmeta> postmeta) {
		this.postmeta = postmeta;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "objectid")
	public Set<Term_relationships> getTerm_relationships() {
		return term_relationships;
	}
	public void setTerm_relationships(Set<Term_relationships> term_relationships) {
		this.term_relationships = term_relationships;
	}
}
