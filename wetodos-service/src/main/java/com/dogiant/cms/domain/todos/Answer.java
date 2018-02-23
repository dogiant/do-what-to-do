package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 选择题答案
 * @author dubiaoqi
 *
 */
@Entity
@Table(name = "todos_answer")
public class Answer implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2732937755102846966L;

	/**
	 * 主键ID
	 */
	private Long id;
	
	/**
	 * 问题ID
	 */
	private Long questionId;
	
	/**
	 * A B C D
	 */
	private String serial;

	/**
	 * 答案内容
	 */
	private String content;

	/**
	 * 是否正确答案
	 */
	private Boolean isCorrect;
	
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

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
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
