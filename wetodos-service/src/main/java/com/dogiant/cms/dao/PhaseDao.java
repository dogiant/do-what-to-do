package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.todos.Phase;

public interface PhaseDao {

	List<Phase> findPhasesByChapterId(Long chapterId);

	List<Phase> findPhasesByBookId(Long bookId);

	Phase save(Phase phase);

	Phase findPhaseById(Long id);

}
