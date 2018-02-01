package com.dogiant.cms.domain.todos;

import java.io.Serializable;

public class Chapter implements Serializable {
	
	private Long id;
	
	private Long bookId;
	
	//古诗词，演讲，文章
	private String contentType;
	
	private String title;
	
	private Integer wordCount;
	
	private Long order;
	
	//问答 语音 图片
	private String taskType;
	


}
