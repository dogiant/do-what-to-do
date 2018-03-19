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
import com.fasterxml.jackson.annotation.JsonFormat;

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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "image_url", length = 256)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "text", length = 32)
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Column(name = "date", length = 16)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
