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
	public List<Phase> findPhasesByChapterId(Long id) {
		return phaseRepo.findPhasesByChapterId(id);
	}

}
