package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.todos.Phase;

public interface PhaseService {

	List<Phase> findPhasesByChapterId(Long id);

	List<Phase> findPhasesByBookId(Long id);

	Phase save(Phase phase);

	Phase findPhaseById(Long id);

}
