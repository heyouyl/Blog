package springMVC.NLoveB.po;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 
 * @ClassName:  Term_relationships   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: likilone(何懮) 
 * @date:   2018年3月29日 下午11:06:49   
 *     
 * @Copyright: 2018 www.028888.net Inc. All rights reserved.
 */
@Entity(name="term_relationships")
@Table(name="blog_term_relationships")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@IdClass(Term_relationships_map.class)
public class Term_relationships {
	private Posts objectid;		//文章id
	private Term_taxonomy taxonomyid;		//分类方法id manytoone  Term_taxonomy
	private int order;			//排序
/*
 * CREATE TABLE IF NOT EXISTS `my028888_term_relationships` (
  `object_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_taxonomy_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `term_order` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`object_id`,`term_taxonomy_id`),
  KEY `term_taxonomy_id` (`term_taxonomy_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
 */
	
	@Id
	@ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name = "object_id", unique = false, nullable = false)
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name ="idGenerator",strategy="foreign" ,parameters=@Parameter(name="property",value="objectid"))
	public Posts getObjectid() {
		return objectid;
	}
	public void setObjectid(Posts objectid) {
		this.objectid = objectid;
	}
	
	@Column(name = "term_order", unique = false, nullable = false,columnDefinition="int(11) NOT NULL DEFAULT '0'")
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	@Id
	//延迟加载：多对一方式
	//关联信息： 急加载
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER,optional = false)
	@JoinColumn(name = "term_taxonomy_id", unique = false, nullable = true)
	public Term_taxonomy getTaxonomyid() {
		return taxonomyid;
	}
	public void setTaxonomyid(Term_taxonomy taxonomyid) {
		this.taxonomyid = taxonomyid;
	}
	
}
