package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.todos.Question;

public interface QuestionService {

	List<Question> getQuestionsByChapterId(Long id);

	Question getQuestionById(Long id);

	void saveQuestion(Question question);

}
