package com.dogiant.cms.domain.todos;

import java.io.Serializable;

public class Answer implements Serializable {
	
	private Long id;
	
	private Long questionId;
	
	private String serial;
	
	private String content;

	private Boolean isCorrect;
	
	
}
