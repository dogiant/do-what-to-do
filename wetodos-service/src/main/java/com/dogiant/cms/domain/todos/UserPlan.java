package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户阅读计划
 * @author dogiant
 *
 */
public class UserPlan implements Serializable {
	
	private String openId;
	
	private String unionId;
	
	private String nickName;
	
	private String avatarUrl;
	
	private String name;
	
	private Long bookId;
	
	private Date startDate;
	
	private Date endDate;
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer status;
	


}
