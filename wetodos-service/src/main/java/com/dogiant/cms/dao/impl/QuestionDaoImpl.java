package com.dogiant.cms.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.QuestionDao;
import com.dogiant.cms.domain.todos.Question;
import com.dogiant.cms.repo.QuestionRepo;

@Service("questionDao")
public class QuestionDaoImpl implements QuestionDao {
	
	@Resource
	private QuestionRepo questionRepo;

	@Override
	public List<Question> getQuestionsByChapterId(Long id) {
		return questionRepo.getQuestionsByChapterId(id);
	}

	@Override
	public void addQuestion(Question question) {
		questionRepo.save(question);
	}

	@Override
	public Question getQuestionById(Long id) {
		return questionRepo.getOne(id);
	}

	@Override
	public void saveQuestion(Question question) {
		questionRepo.save(question);
	}

}
