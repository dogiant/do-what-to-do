package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.todos.Answer;

public interface AnswerDao {

	List<Answer> getAnswersByQuestionId(Long id);

	void saveAnswer(Answer answer);

	Answer getAnswerById(Long id);

}
