package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.PhaseDao;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.service.PhaseService;

@Service("phaseSevice")
public class PhaseServiceImpl implements PhaseService {
	
	@Autowired
	private PhaseDao phaseDao;

	@Override
	public List<Phase> findPhasesByChapterId(Long chapterId) {
		return phaseDao.findPhasesByChapterId(chapterId);
	}

	@Override
	public List<Phase> findPhasesByBookId(Long bookId) {
		return phaseDao.findPhasesByBookId(bookId);
	}

	@Override
	public Phase save(Phase phase) {
		return phaseDao.save(phase);
	}

	@Override
	public Phase findPhaseById(Long id) {
		return phaseDao.findPhaseById(id);
	}

}
