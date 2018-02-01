package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户每日任务完成情况详情
 * @author dogiant
 *
 */
public class UserScheduleDetail implements Serializable {
	
	private String openId;
	
	private String unionId;
	
	private Long bookId;
	
	private Long chapterId;
	
	private Long scheduleId;
	
	//任务完成情况 0未完 1当天完成 2补录
	private Integer complete;
	
	private String contentType;
	
	private String content;
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer status;
	

}
