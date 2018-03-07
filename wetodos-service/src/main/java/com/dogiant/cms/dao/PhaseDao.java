package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.todos.Phase;

public interface PhaseDao {

	List<Phase> findPhasesByChapterId(Long id);

}
