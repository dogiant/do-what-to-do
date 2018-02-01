package com.dogiant.cms.domain.todos;

import java.io.Serializable;
import java.util.Date;

public class Phase implements Serializable {
	
	private Long id;
	
	private Long bookId;
	
	private Long chapterId;
	
	private Long order;
	
	private String content;
	
	//展示的内容类型， image, text,
	private String contentType;
	
	private String alignType;
	
	private String editor;
	
	private Date ctime;
	
	private Date mtime;
	
	private Integer status;

}
