package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.AnswerDao;
import com.dogiant.cms.domain.todos.Answer;
import com.dogiant.cms.service.AnswerService;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
	
	@Autowired
	private AnswerDao answerDao;

	@Override
	public List<Answer> getAnswersByQuestionId(Long id) {
		return answerDao.getAnswersByQuestionId(id);
	}

	@Override
	public void saveAnswer(Answer answer) {
		answerDao.saveAnswer(answer);
	}

	@Override
	public Answer getAnswerById(Long id) {
		return answerDao.getAnswerById(id);
	}


}
