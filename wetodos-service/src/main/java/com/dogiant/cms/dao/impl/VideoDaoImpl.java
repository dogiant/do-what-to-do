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

import com.dogiant.cms.dao.VideoDao;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.domain.website.Video;
import com.dogiant.cms.repo.VideoRepo;

@Service("videoDao")
public class VideoDaoImpl implements VideoDao {

	@Resource
	VideoRepo videoRepo;
	
	@Override
	public void save(Video video) {
		videoRepo.save(video);
	}

	@Override
	public void delete(Video video) {
		videoRepo.delete(video);
	}

	@Override
	public List<Video> getVideoList(Video video) {
		List<Video> list = new ArrayList<Video>();
		list = videoRepo.getVideoList();
		return list;
	}

	@Override
	public QueryResult<Video> getVideoQueryResult(Integer start,
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
		Specification<Video> spc = this
				.getSearchSpecification(searchValue);

		Page<Video> page = videoRepo.findAll(spc, pageable);
		QueryResult<Video> queryResult = new QueryResult<Video>();
		queryResult.setRecordnum(page.getTotalElements());
		queryResult.setResult(page.getContent());
		return queryResult;
	}
	
	private Specification<Video> getSearchSpecification(
			final String searchValue) {
		return new Specification<Video>() {
			@Override
			public Predicate toPredicate(Root<Video> paramRoot,
					CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

//				list.add(paramCriteriaBuilder.ge(
//						paramRoot.get("status").as(Number.class), 0));
				if (StringUtils.isNoneEmpty(searchValue))
					list.add(paramCriteriaBuilder.like(paramRoot.get("videoName")
							.as(String.class), "%" + searchValue + "%"));
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

}
