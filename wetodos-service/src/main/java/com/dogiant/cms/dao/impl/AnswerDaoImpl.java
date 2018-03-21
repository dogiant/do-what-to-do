package com.dogiant.cms.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.AnswerDao;
import com.dogiant.cms.domain.todos.Answer;
import com.dogiant.cms.repo.AnswerRepo;

@Service("answerDao")
public class AnswerDaoImpl implements AnswerDao {
	
	@Resource
	private AnswerRepo answerRepo;

	@Override
	public List<Answer> getAnswersByQuestionId(Long id) {
		return answerRepo.getAnswersByQuestionId(id);
	}

	@Override
	public void saveAnswer(Answer answer) {
		answerRepo.save(answer);
	}

	@Override
	public Answer getAnswerById(Long id) {
		return answerRepo.getOne(id);
	}


}
