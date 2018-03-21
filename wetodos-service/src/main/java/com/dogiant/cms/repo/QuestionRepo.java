package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.Question;

public interface QuestionRepo extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

	@Transactional(readOnly = true)
	@Query("select o from Question o where o.chapterId =:chapterId and o.status>=0 order by o.id asc")
	List<Question> getQuestionsByChapterId(@Param("chapterId") Long id);

}
