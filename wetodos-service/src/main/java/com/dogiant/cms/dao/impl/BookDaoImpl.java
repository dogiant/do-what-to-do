package com.dogiant.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.BookDao;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.repo.BookRepo;

@Service("bookDao")
public class BookDaoImpl implements BookDao {
	
	@Resource
	private BookRepo bookRepo;

	@Override
	public void addBook(Book book) {
		bookRepo.save(book);
	}

	@Override
	public Book getBook(Long id) {
		return bookRepo.getOne(id);
	}

	@Override
	public void updateBook(Book book) {
		bookRepo.save(book);
	}

	@Override
	public QueryResult<Book> getBookQueryResult(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue) {
		Sort sort = new Sort(Direction.DESC, "ctime");
		if (StringUtils.isNotEmpty(orderName)
				&& StringUtils.isNotEmpty(orderDir)) {
			if ("asc".equalsIgnoreCase(orderDir)) {
				sort = new Sort(Direction.ASC, orderName);
			} else if ("desc".equalsIgnoreCase(orderDir)) {
				sort = new Sort(Direction.DESC, orderName);
			}
		}

		int pageNo = (start / length) > 0 ? (start / length) - 1 : 0;
		Pageable pageable = new PageRequest(pageNo, length, sort);
		Specification<Book> spc = this
				.getSearchSpecification(searchValue);

		Page<Book> page = bookRepo.findAll(spc, pageable);
		QueryResult<Book> queryResult = new QueryResult<Book>();
		queryResult.setRecordnum(page.getTotalElements());
		queryResult.setResult(page.getContent());
		return queryResult;
	}

	private Specification<Book> getSearchSpecification(String searchValue) {
		return new Specification<Book>() {
			@Override
			public Predicate toPredicate(Root<Book> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				list.add(paramCriteriaBuilder.ge(paramRoot.get("status").as(Number.class), 0));
				if (StringUtils.isNoneEmpty(searchValue))
					list.add(paramCriteriaBuilder.like(paramRoot.get("title")
							.as(String.class), "%" + searchValue + "%"));
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

	@Override
	public List<Book> getBookListByIds(Long[] ids) {
		return bookRepo.getBookListByIds(ids);
	}

}
