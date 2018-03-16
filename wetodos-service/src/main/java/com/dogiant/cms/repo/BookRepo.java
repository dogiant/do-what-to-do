package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.todos.Book;

public interface BookRepo extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

	@Transactional(readOnly = true)
	@Query("select o from Book o where o.id in :ids and o.status>=0 order by id asc")
	List<Book> getBookListByIds(@Param("ids")Long[] ids);

}
