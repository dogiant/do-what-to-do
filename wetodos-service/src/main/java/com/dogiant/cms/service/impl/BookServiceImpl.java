package com.dogiant.cms.service.impl;

import org.springframework.stereotype.Service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	@Override
	public DataTablesResult<Book> getBookDataTablesResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book getBook(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub

	}

}
