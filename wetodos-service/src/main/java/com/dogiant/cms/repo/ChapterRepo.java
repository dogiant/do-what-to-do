package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.Chapter;

public interface ChapterRepo extends JpaRepository<Chapter, Long>, JpaSpecificationExecutor<Chapter> {

	@Transactional(readOnly = true)
	@Query("select o from Chapter o where o.bookId =:bookId and o.status>=0 order by o.id asc")
	List<Chapter> findChaptersByBookId(@Param("bookId")Long bookId);

}
