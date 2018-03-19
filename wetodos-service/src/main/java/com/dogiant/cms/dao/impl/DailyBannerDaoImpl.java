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

import com.dogiant.cms.dao.DailyBannerDao;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.DailyBanner;
import com.dogiant.cms.repo.DailyBannerRepo;

@Service("dailyBannerDao")
public class DailyBannerDaoImpl implements DailyBannerDao {
	
	@Resource
	private DailyBannerRepo dailyBannerRepo;

	@Override
	public void addDailyBanner(DailyBanner dailyBanner) {
		dailyBannerRepo.save(dailyBanner);
	}

	@Override
	public DailyBanner getDailyBanner(Long id) {
		return dailyBannerRepo.getOne(id);
	}

	@Override
	public void updateDailyBanner(DailyBanner dailyBanner) {
		dailyBannerRepo.save(dailyBanner);
	}

	@Override
	public QueryResult<DailyBanner> getDailyBannerQueryResult(Integer start,
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
		Specification<DailyBanner> spc = this
				.getSearchSpecification(searchValue);

		Page<DailyBanner> page = dailyBannerRepo.findAll(spc, pageable);
		QueryResult<DailyBanner> queryResult = new QueryResult<DailyBanner>();
		queryResult.setRecordnum(page.getTotalElements());
		queryResult.setResult(page.getContent());
		return queryResult;
	}

	private Specification<DailyBanner> getSearchSpecification(String searchValue) {
		return new Specification<DailyBanner>() {
			@Override
			public Predicate toPredicate(Root<DailyBanner> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				list.add(paramCriteriaBuilder.ge(paramRoot.get("status").as(Number.class), 0));
				if (StringUtils.isNoneEmpty(searchValue))
					list.add(paramCriteriaBuilder.like(paramRoot.get("text")
							.as(String.class), "%" + searchValue + "%"));
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

}
