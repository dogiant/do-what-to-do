package com.dogiant.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.BookDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookDao bookDao;

	@Override
	public DataTablesResult<Book> getBookDataTablesResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue) {
		QueryResult<Book> queryResult = bookDao.getBookQueryResult(start, length, orderName, orderDir, searchValue);
		
		DataTablesResult<Book> dataTableResult = new DataTablesResult<Book>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

	@Override
	public void addBook(Book book) {
		bookDao.addBook(book);
	}

	@Override
	public Book getBook(Long id) {
		return bookDao.getBook(id);
	}

	@Override
	public void updateBook(Book book) {
		bookDao.updateBook(book);

	}

}
