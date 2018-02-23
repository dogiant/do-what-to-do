package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	private Long scheduleId;

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
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

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(Integer completeStatus) {
		this.completeStatus = completeStatus;
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
