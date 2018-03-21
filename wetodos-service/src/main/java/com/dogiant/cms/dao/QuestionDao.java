package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.todos.Question;

public interface QuestionDao {

	List<Question> getQuestionsByChapterId(Long id);

	void addQuestion(Question question);

	Question getQuestionById(Long id);

	void saveQuestion(Question question);

}
