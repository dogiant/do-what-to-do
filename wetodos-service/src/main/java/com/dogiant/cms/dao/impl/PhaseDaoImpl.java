package com.dogiant.cms.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.PhaseDao;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.repo.PhaseRepo;

@Service("phaseDao")
public class PhaseDaoImpl implements PhaseDao {
	
	@Resource
	private PhaseRepo phaseRepo;

	@Override
	public List<Phase> findPhasesByChapterId(Long chapterId) {
		return phaseRepo.findPhasesByChapterId(chapterId);
	}

	@Override
	public List<Phase> findPhasesByBookId(Long bookId) {
		return phaseRepo.findPhasesByBookId(bookId);
	}

	@Override
	public Phase save(Phase phase) {
		return phaseRepo.save(phase);
	}

	@Override
	public Phase findPhaseById(Long id) {
		return phaseRepo.findOne(id);
	}

}
