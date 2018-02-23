package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 章节中的段落组成
 * @author dubiaoqi
 *
 */
@Entity
@Table(name = "todos_phase")
public class Phase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5075727693198228692L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 图书ID
	 */
	private Long bookId;
	
	/**
	 * 章回ID
	 */
	private Long chapterId;
	
	/**
	 * 序号
	 */
	private Long order;
	
	/**
	 * 展示的内容类型， text, image, audio, video?
	 */
	private String contentType;
	
	/**
	 * 内容
	 */
	private String content;

	/**
	 * 对齐方式 居 左中右
	 */
	private String alignType;
	
	/**
	 * 编辑
	 */
	private String editor;
	
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

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAlignType() {
		return alignType;
	}

	public void setAlignType(String alignType) {
		this.alignType = alignType;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
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
