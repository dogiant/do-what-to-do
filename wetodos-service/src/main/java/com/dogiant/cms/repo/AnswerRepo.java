package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.Answer;

public interface AnswerRepo extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {

	@Transactional(readOnly = true)
	@Query("select o from Answer o where o.questionId =:questionId and o.status>=0 order by o.serial asc")
	List<Answer> getAnswersByQuestionId(@Param("questionId") Long id);

}
