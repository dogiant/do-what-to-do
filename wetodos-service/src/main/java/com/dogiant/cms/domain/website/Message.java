package com.dogiant.cms.domain.website;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "message")
@Inheritance(strategy = InheritanceType.JOINED)
public class Message {
	// id
	private Long id;
	// 消息类型
	private String msgType;
	// 用户名
	private String userName;
	// 是否共享
	private Boolean share;
	//0 先发后审 1审核通过 -1先审后发 -2自主删除 -3强制删除
	private Integer status;
	//创建时间
	private Date ctime;
	//修改时间
	private Date mtime;
	/**
	 * 分组下拉列表选择（用于群发消息）
	 */
	private String groupSelect;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "msg_type", nullable = false, length = 32)
	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Column(name = "user_name", nullable = false, length = 60)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "share", nullable = false)
	public Boolean getShare() {
		return share;
	}

	public void setShare(Boolean share) {
		this.share = share;
	}
	
	//0 先发后审 1审核通过 -1先审后发 -2自主删除 -3强制删除
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
	
	@Transient
	public String getGroupSelect() {
		return groupSelect;
	}

	public void setGroupSelect(String groupSelect) {
		this.groupSelect = groupSelect;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}
}
