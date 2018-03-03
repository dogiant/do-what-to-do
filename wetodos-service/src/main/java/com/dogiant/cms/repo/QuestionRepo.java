package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.todos.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

}
