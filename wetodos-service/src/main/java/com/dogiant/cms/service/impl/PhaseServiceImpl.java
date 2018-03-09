package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.PhaseDao;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.service.PhaseSevice;

@Service("phaseSevice")
public class PhaseServiceImpl implements PhaseSevice {
	
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

}
