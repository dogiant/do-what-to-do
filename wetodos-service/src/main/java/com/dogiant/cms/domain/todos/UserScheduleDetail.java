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
 * 用户每日任务完成情况详情
 * 
 * @author dogiant
 *
 */
@Entity
@Table(name = "todos_user_schedule_detail")
public class UserScheduleDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3568934308083827664L;
	
	/**
	 * 主键ID
	 */
	private Long id;

	/**
	 * 用户openId
	 */
	private String openId;

	/**
	 * 微信用户唯一标识
	 */
	private String unionId;

	/**
	 * 图书ID
	 */
	private Long bookId;

	/**
	 * 章回ID
	 */
	private Long chapterId;

	/**
	 * 每日时间表ID
	 */
	private Long userScheduleId;

	/**
	 * 任务完成情况 0未完成 1当天完成 2补录
	 */
	private Integer completeStatus;

	/**
	 * 内容类型 （text,image,audio）
	 */
	private String contentType;

	/**
	 * 内容（图）
	 */
	private String content;

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

	@Column(name = "open_id")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "union_id")
	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
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

	@Column(name = "user_schedule_id")
	public Long getUserScheduleId() {
		return userScheduleId;
	}

	public void setUserScheduleId(Long userScheduleId) {
		this.userScheduleId = userScheduleId;
	}

	@Column(name = "complete_status")
	public Integer getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(Integer completeStatus) {
		this.completeStatus = completeStatus;
	}

	@Column(name = "content_type", length = 16)
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
