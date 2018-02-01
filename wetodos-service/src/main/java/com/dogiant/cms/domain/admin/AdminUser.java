package com.dogiant.cms.domain.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * AdminUser entity.
 * 
 * @author dogiant
 */
@Entity
@Table(name = "admin_user")
public class AdminUser implements java.io.Serializable {

	private static final long serialVersionUID = 4740392243372990696L;
	private Integer userId;
	private String userName;
	private String password;

	private String nickname;
	private String realName;
	private String email;
	private String phone;
	private String mobile;
	
	private Date lastLoginTime;
	private String lastLoginIp;
	
	private Date ctime;
	private Date mtime;
	// 有效标识
	private Integer isValid;
	

	private Set<Privilege> privileges = new HashSet<Privilege>();
	private Set<Role> roles = new HashSet<Role>();

	public AdminUser() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Transient
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "admin_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "user_name", nullable = false, length = 60)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "email", length = 60)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "real_name", length = 50)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "phone", length = 50)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Transient
	public String getLastLoginTimeFormat(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		if (lastLoginTime == null) {
			lastLoginTime = new Date();
		}
		return sdf.format(lastLoginTime);
	}
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_time")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "last_login_ip",length=36)
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	@Column(name = "is_valid")
	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	@Transient
	public boolean hasRole(Integer roleId) {
		Role role = new Role(roleId);
		if (this.getRoles().contains(role)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "AdminUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", nickname="
				+ nickname + ", realName=" + realName + ", email=" + email + ", phone=" + phone + ", mobile=" + mobile
				+ ", lastLoginTime=" + lastLoginTime + ", lastLoginIp=" + lastLoginIp + ", ctime=" + ctime + ", mtime="
				+ mtime + ", isValid=" + isValid + ", privileges=" + privileges + ", roles=" + roles + "]";
	}

}