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

import com.fasterxml.jackson.annotation.JsonFormat;

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
	 * 展示的内容类型， text, image, audio, video?
	 */
	private String contentType;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 资源地址
	 */
	private String uri;

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

	@Column(name = "chapter_id")
	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
		this.chapterId = chapterId;
	}

	@Column(name = "content_type", length = 32)
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "content", length = 1024)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "uri")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Column(name = "align_type", length = 16)
	public String getAlignType() {
		return alignType;
	}

	public void setAlignType(String alignType) {
		this.alignType = alignType;
	}

	@Column(name = "editor", length = 64)
	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
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
