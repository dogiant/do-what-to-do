package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.todos.Phase;

public interface PhaseSevice {

	List<Phase> findPhasesByChapterId(Long id);

	List<Phase> findPhasesByBookId(Long id);

}
