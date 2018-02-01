package com.dogiant.cms.domain.website;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * ArticleCat entity.
 * 
 * @author dogiant
 */
@Entity
@Table(name = "article_cat")
public class ArticleCat implements java.io.Serializable {

	private static final long serialVersionUID = 4218450636537166140L;

	/**
	 * 栏目ID
	 */
	private Long catId;
	/**
	 * 栏目代码(英文名)
	 */
	private String catCode;
	/**
	 * 栏目名称
	 */
	private String catName;
	/**
	 * 1 系统设定类型 不允许删改
	 */
	private int catType;
	/**
	 * 是否终极正文页栏目
	 */
	private Boolean isTextCat;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 描述
	 */
	private String catDesc;
	/**
	 * 排序字段
	 */
	private Integer sortOrder;
	/**
	 * 是否显示
	 */
	private Boolean showInNav;
	/**
	 * 创建时间
	 */
	private Date ctime;
	/**
	 * 修改时间
	 */
	private Date mtime;
	/**
	 * 子栏目
	 */
	private List<ArticleCat> childCat;
	/**
	 * 父栏目 顶级栏目为null
	 */
	private ArticleCat parent;
	/**
	 * 栏目下图文文章
	 */
	private List<ArticleItem> articles;
	
	/**
	 * 层级
	 */
	private int level;

	public ArticleCat() {
		super();
	}
	
	public ArticleCat(String catCode, String catName, int catType, Boolean isTextCat, String keywords, String catDesc,
			Integer sortOrder, Boolean showInNav, Date ctime, Date mtime) {
		super();
		this.catCode = catCode;
		this.catName = catName;
		this.catType = catType;
		this.isTextCat = isTextCat;
		this.keywords = keywords;
		this.catDesc = catDesc;
		this.sortOrder = sortOrder;
		this.showInNav = showInNav;
		this.ctime = ctime;
		this.mtime = mtime;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cat_id", unique = true, nullable = false)
	public Long getCatId() {
		return this.catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	@Column(name = "cat_code", nullable = true, length = 128)
	public String getCatCode() {
		return catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	@Column(name = "cat_name", nullable = false, length = 50)
	public String getCatName() {
		return this.catName;
	}
	
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	@Transient
	public String getCatNameShow() {
		StringBuffer strBuf = new StringBuffer();
		for (int x = 0; x < this.level; x++) {
			strBuf.append("　　");
		}
		return strBuf.toString()+this.catName;
	}
	
	@Column(name = "cat_type",nullable = true)
	public int getCatType() {
		return catType;
	}

	public void setCatType(int catType) {
		this.catType = catType;
	}
	
	@Transient
	public String getCatTypeDesc(){
		switch (catType) {
		case 0:
			return "系统设定";
		case 1:
			return "自由增设";
		default:
			return "未知状态";
		}
	}

	@Column(name = "is_text_cat", nullable = true)
	public Boolean getIsTextCat() {
		return isTextCat;
	}

	public void setIsTextCat(Boolean isTextCat) {
		this.isTextCat = isTextCat;
	}

	@Column(name = "keywords", nullable = true)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "cat_desc", nullable = true)
	public String getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	@Column(name = "sort_order", nullable = true)
	public Integer getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	@Column(name = "show_in_nav", nullable = true)
	public Boolean getShowInNav() {
		return this.showInNav;
	}

	public void setShowInNav(Boolean showInNav) {
		this.showInNav = showInNav;
	}

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	public ArticleCat getParent() {
		return parent;
	}

	public void setParent(ArticleCat parent) {
		this.parent = parent;
	}
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mtime")
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy = "parent", fetch = FetchType.LAZY)
	public List<ArticleCat> getChildCat() {
		return childCat;
	}

	public void setChildCat(List<ArticleCat> childCat) {
		this.childCat = childCat;
	}

	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE }, mappedBy="articleCat", fetch=FetchType.LAZY)
	//@JoinColumn(name="cat_id", referencedColumnName = "cat_id")
	public List<ArticleItem> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleItem> articles) {
		this.articles = articles;
	}
	
	@Transient
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
	
}