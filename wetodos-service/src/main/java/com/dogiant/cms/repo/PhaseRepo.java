package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.Phase;

public interface PhaseRepo extends JpaRepository<Phase, Long>, JpaSpecificationExecutor<Phase> {

}
