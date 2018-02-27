package com.dogiant.cms.service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.todos.Book;

public interface BookService {

	DataTablesResult<Book> getBookDataTablesResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);

	void addBook(Book book);

	Book getBook(Long id);

	void updateBook(Book book);

}
