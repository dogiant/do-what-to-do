package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读计划时间表，每日任务通知设置 几点 几分
 * @author dogiant
 *
 */
public class UserScheduleNotify implements Serializable {
	
	private String openId;
	
	private String unionId;
	
	private Integer hour;
	
	private Integer minute;
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer status;
	
	


}
