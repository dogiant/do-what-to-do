package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 章节
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
	 * 内容类型 //古诗词，演讲，文章
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
	 * 字数统计
	 */
	private Integer wordCount;
	
	/**
	 * 排序序号
	 */
	private Long order;
	
	/**
	 * 阅后任务类型  //qa问答 audio语音 image图片
	 */
	private String taskType;
	
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

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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
