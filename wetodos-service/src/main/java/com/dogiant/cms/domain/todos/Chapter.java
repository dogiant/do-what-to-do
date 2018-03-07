package com.dogiant.cms.domain.todos;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 章节
 * 
 * @author dubiaoqi
 *
 */
@Entity
@Table(name = "todos_chapter")
public class Chapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8098121311362194578L;

	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 图书ID
	 */
	private Long bookId;

	/**
	 * 内容类型 //poem古诗词，speech演讲，article文章
	 */
	private String contentType;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 子标题
	 */
	private String subTitle;

	/**
	 * 阅后任务类型 //choice选择 image图片 audio语音 vedio视频
	 */
	private String taskTypes;
	
	/**
	 * 包含的章节列表
	 */
	private List<Phase> phases;

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

	@Column(name = "book_id")
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	@Column(name = "content_type", length = 32)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "title", length = 32)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "sub_title", length = 64)
	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	@Column(name = "task_types", length = 64)
	public String getTaskTypes() {
		return taskTypes;
	}

	public void setTaskTypes(String taskTypes) {
		this.taskTypes = taskTypes;
	}
	
	@Transient
	public List<Phase> getPhases() {
		return phases;
	}

	public void setPhases(List<Phase> phases) {
		this.phases = phases;
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

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
