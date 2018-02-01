package com.dogiant.cms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ArticleItemDao;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.repo.ArticleItemRepo;

@Service("articleItemDao")
public class ArticleItemDaoImpl implements ArticleItemDao {

	@Autowired
	private ArticleItemRepo articleItemRepo;

	@Override
	public void save(ArticleItem articleItem) {
		articleItemRepo.save(articleItem);
	}

	@Override
	public ArticleItem getArticleItem(Long id) {
		return articleItemRepo.getOne(id);
	}

	@Override
	public QueryResult<ArticleItem> getArticleItemQueryResult(Integer start,
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
		Specification<ArticleItem> spc = this
				.getSearchSpecification(searchValue);

		Page<ArticleItem> page = articleItemRepo.findAll(spc, pageable);
		QueryResult<ArticleItem> queryResult = new QueryResult<ArticleItem>();
		queryResult.setRecordnum(page.getTotalElements());
		queryResult.setResult(page.getContent());
		return queryResult;
	}

	private Specification<ArticleItem> getSearchSpecification(
			final String searchValue) {
		return new Specification<ArticleItem>() {
			@Override
			public Predicate toPredicate(Root<ArticleItem> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				list.add(paramCriteriaBuilder.ge(
						paramRoot.get("status").as(Number.class), 0));
				if (StringUtils.isNoneEmpty(searchValue))
					list.add(paramCriteriaBuilder.like(paramRoot.get("title")
							.as(String.class), "%" + searchValue + "%"));
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

	@Override
	public Page<ArticleItem> getPagedArticleItem(Integer pageNo,
			Integer pageRows, String catCode) {
		Sort sort = new Sort(Direction.DESC, "ctime");
		pageNo = pageNo - 1 < 0 ? 0 : pageNo - 1;
		Pageable pageable = new PageRequest(pageNo, pageRows, sort);
		
		Specification<ArticleItem> spc = this.getCatCodeSpecification(catCode);
		return articleItemRepo.findAll(spc, pageable);

	}

	private Specification<ArticleItem> getCatCodeSpecification(
			final String catCode) {
		return new Specification<ArticleItem>() {
			@Override
			public Predicate toPredicate(Root<ArticleItem> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				list.add(paramCriteriaBuilder.ge(
						paramRoot.get("status").as(Number.class), 0));
				list.add(paramCriteriaBuilder.equal(paramRoot.get("articleCat").get("catCode")
						.as(String.class), catCode));
				
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}
	
	private Specification<ArticleItem> getSpecification(final List<String> catCodes) {
		return new Specification<ArticleItem>() {
			@Override
			public Predicate toPredicate(Root<ArticleItem> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				list.add(paramCriteriaBuilder.ge(
						paramRoot.get("status").as(Number.class), 0));
				Expression<String> exp = paramRoot.get("articleCat").get("catCode");
				Predicate predicate = exp.in(catCodes);
				list.add(predicate);
				
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}
	

	@Override
	public ArticleItem getArticleItemValidDataById(Long id) {
		return articleItemRepo.getArticleItemValidDataById(id);
	}

	@Override
	public List<ArticleItem> getLatestPost(List<String> catCodes, int size) {
		
		Sort sort = new Sort(Direction.DESC, "ctime");
		Pageable pageable = new PageRequest(0, size, sort);
		
		Specification<ArticleItem> spc = this.getSpecification(catCodes);
		
		return articleItemRepo.findAll(spc, pageable).getContent();

	}
	
	@Override
	public List<ArticleItem> getRecommendItems(List<String> catCodes, int size){
		Sort sort = new Sort(Direction.DESC, "ctime");
		Pageable pageable = new PageRequest(0, size, sort);
		
		Specification<ArticleItem> spc = this.getSpecificationRecommendItems(catCodes);
		
		return articleItemRepo.findAll(spc, pageable).getContent();

	}

	
	private Specification<ArticleItem> getSpecificationRecommendItems(final List<String> catCodes) {
		return new Specification<ArticleItem>() {
			@Override
			public Predicate toPredicate(Root<ArticleItem> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();
				list.add(paramCriteriaBuilder.ge(
						paramRoot.get("status").as(Number.class), 0));
				list.add(paramCriteriaBuilder.equal(paramRoot.get("recommend").as(Boolean.class),true));
				Expression<String> exp = paramRoot.get("articleCat").get("catCode");
				Predicate predicate = exp.in(catCodes);
				list.add(predicate);
				
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

}
