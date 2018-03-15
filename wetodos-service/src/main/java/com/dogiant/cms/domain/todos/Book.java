package com.dogiant.cms.domain.todos;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.dogiant.cms.config.ImageConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 书
 * @author dubiaoqi
 *
 */
@Entity
@Table(name = "todos_book")
public class Book implements Serializable{
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 7060963338275045911L;

	/**
	 * 主键
	 */
	private Long id;
	
	/**
	 * 类型 1管理员创建 2用户上传发布
	 */
	private Integer type;
	
	/**
	 * 标签
	 */
	private String tags;
	
	/**
	 * 书名
	 */
	private String title;

	/**
	 * 封面url
	 */
	private String coverPicUrl;

	/**
	 * 作者
	 */
	private String author;

	/**
	 * 描述
	 */
	private String digest;
	
	/**
	 * 操作者
	 */
	private String operator;
	
	/**
	 * 创建时间
	 */
	private Date ctime;
	
	/**
	 * 修改时间
	 */
	private Date mtime;
	
	/**
	 * 状态 小于0无效
	 */
	private Integer status;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "tags")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	@Column(name = "title", length = 128)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "cover_pic_url")
	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}

	@Column(name = "author", length = 64)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "digest")
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	@Column(name = "operator", length = 64)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mtime")
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Transient
	public String getBookShow() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"thumbnail\" id=\"news_thumbnail\">");
		sb.append("<h4 id=\"news_title\" >"+ getTitle() +"</h4>");
		sb.append("<div id=\"cover_wrapper\">");
		sb.append("<img src=\""+ImageConfig.imageUrlPrefix + getCoverPicUrl()+"\" id=\"news_cover\" onerror=\"this.style.display='none'\" />");
		sb.append("<i>封面图片</i>");
		sb.append("</div>");
		sb.append("<div class=\"caption\">");
		sb.append("<p id=\"news_digest\" >");
		sb.append(getDigest());
		sb.append("</p>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}
	
	@Transient
	public String getTypeDesc() {
		if (this.getType() != null) {
			return this.getType() == 1 ? "管理员录入" : "用户上传";
		}
		return "";
	}
	
	/**
	 * 返回状态描述
	 * @return
	 */
	@Transient
	public String getStatusDescription() {
		switch(status){
			case 0:
				return "正常显示";
			case 1:
				return "审核通过";
			case -1:
				return "等待审核";
			case -2:
				return "自主删除";
			case -3:
				return "强制删除";
			default:
				return "未知状态";
		}
	}
}
