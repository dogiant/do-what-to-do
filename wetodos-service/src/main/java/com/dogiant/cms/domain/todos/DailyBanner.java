package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 每日一图
 * @author dubiaoqi
 *
 */
@Entity
@Table(name = "todos_daily_banner")
public class DailyBanner implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6467556005252656551L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 图片Url
	 */
	private String imageUrl;
	
	/**
	 * 文字
	 */
	private String text;
	
	/**
	 * 日期(yyyy-MM-dd)
	 */
	private String date;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
