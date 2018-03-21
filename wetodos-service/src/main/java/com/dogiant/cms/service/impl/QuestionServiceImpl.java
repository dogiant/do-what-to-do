package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.QuestionDao;
import com.dogiant.cms.domain.todos.Question;
import com.dogiant.cms.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionDao;

	@Override
	public List<Question> getQuestionsByChapterId(Long id) {
		return questionDao.getQuestionsByChapterId(id);
	}

	@Override
	public Question getQuestionById(Long id) {
		return questionDao.getQuestionById(id);
	}

	@Override
	public void saveQuestion(Question question) {
		questionDao.saveQuestion(question);
		
	}

}
