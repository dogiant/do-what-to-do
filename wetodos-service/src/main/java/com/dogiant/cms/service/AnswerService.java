package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.todos.Answer;

public interface AnswerService {

	List<Answer> getAnswersByQuestionId(Long id);

	void saveAnswer(Answer answer);

	Answer getAnswerById(Long id);

}
