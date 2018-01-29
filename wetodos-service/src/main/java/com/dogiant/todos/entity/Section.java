package com.dogiant.todos.entity;

import com.dogiant.todos.enums.TextAlign;

public class Section {
	
	private Long id;
	
	private Long bookId;
	
	private Long articleId;
	
	private String content;
	
	//展示的内容类型， imageUrl,text,
	private String type;
	
	private TextAlign align;
	
	private String editor;

}
