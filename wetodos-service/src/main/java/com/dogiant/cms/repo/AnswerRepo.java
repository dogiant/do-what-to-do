package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.Answer;

public interface AnswerRepo extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

}
