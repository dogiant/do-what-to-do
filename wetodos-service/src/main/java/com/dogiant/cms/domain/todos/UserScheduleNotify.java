package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学习计划时间表，每日任务通知设置 几点 几分 例如早上5点半 5:30
 * 
 * @author dogiant
 *
 */
@Entity
@Table(name = "todos_user_schedule_notify")
public class UserScheduleNotify implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6842565784707680974L;

	/**
	 * 用户openId
	 */
	private String openId;

	/**
	 * 微信用户唯一标识
	 */
	private String unionId;

	/**
	 * 小时(24小时制)
	 */
	private Integer hour;

	/**
	 * 分钟(0-59 10整数)
	 */
	private Integer minute;

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

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
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
