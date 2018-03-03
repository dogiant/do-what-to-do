package com.dogiant.cms.dao;

import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.Book;

public interface BookDao {

	void addBook(Book book);

	Book getBook(Long id);

	void updateBook(Book book);

	QueryResult<Book> getBookQueryResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);
	
}
