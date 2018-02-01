package com.dogiant.cms.domain.admin;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * AdminLog entity.
 * 
 * @author dogiant
 */
@Entity
@Table(name = "admin_log")
public class AdminLog implements java.io.Serializable {

	private static final long serialVersionUID = 5009321074410040046L;
	private Integer logId;
	private Date logTime;
	private AdminUser user;
	private String logInfo;
	private String ipAddress;

	public AdminLog() {
	}

	public AdminLog(Integer logId, Date logTime, AdminUser user,
			String logInfo, String ipAddress) {
		super();
		this.logId = logId;
		this.logTime = logTime;
		this.user = user;
		this.logInfo = logInfo;
		this.ipAddress = ipAddress;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "log_id", unique = true, nullable = false)
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="log_time")
	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user_id")
	public AdminUser getUser() {
		return user;
	}

	public void setUser(AdminUser user) {
		this.user = user;
	}

	@Column(name = "log_info", nullable = false)
	public String getLogInfo() {
		return this.logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	@Column(name = "ip_address", nullable = false, length = 23)
	public String getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logId == null) ? 0 : logId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminLog other = (AdminLog) obj;
		if (logId == null) {
			if (other.logId != null)
				return false;
		} else if (!logId.equals(other.logId))
			return false;
		return true;
	}

}