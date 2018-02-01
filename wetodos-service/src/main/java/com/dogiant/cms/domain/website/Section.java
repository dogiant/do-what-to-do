package com.dogiant.cms.domain.website;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "section")
public class Section implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5610535332769688674L;

	private Long id;

	private String code;

	private String name;

	private Integer type;
	
	private String content;
	
	private Integer status;
	
	//创建时间
	private Date ctime;
	//修改时间
	private Date mtime;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Lob
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Transient
	public String getTypeDesc() {
		switch (type) {
		case 0:
			return "系统设定";
		case 1:
			return "自由增设";
		default:
			return "未知状态";
		}
	}
	
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
	
	/**
	 * 发表时间
	 * @return
	 */
	@Transient
	public String getPubTime(){
		return new SimpleDateFormat("yy-MM-dd HH:mm").format(mtime);
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.DEFAULT_STYLE);
	}
}
