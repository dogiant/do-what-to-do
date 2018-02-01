package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读计划时间表，每日任务完成情况
 * @author dogiant
 *
 */
public class UserSchedule implements Serializable {
	
	private String openId;
	
	private String unionId;
	
	private Long bookId;
	
	private Long chapterId;
	
	//任务完成情况 0未完 1当天完成 2补录
	private Integer complete;
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer status;
	
	


}
