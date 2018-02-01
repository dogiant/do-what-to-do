package com.dogiant.cms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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

import com.dogiant.cms.dao.SectionDao;
import com.dogiant.cms.domain.website.Section;
import com.dogiant.cms.repo.SectionRepo;

@Service("sectionDao")
public class SectionDaoImpl implements SectionDao {

	@Autowired
	private SectionRepo sectionRepo;

	@Override
	public void save(Section section) {
		sectionRepo.save(section);
	}

	@Override
	public void update(Section section) {
		sectionRepo.save(section);
	}

	@Override
	public Section getSection(Long id) {
		return sectionRepo.findOne(id);
	}

	@Override
	public void delete(Long id) {
		Section section = this.getSection(id);
		if (section != null && section.getType()!=0) {
			section.setStatus(-3);
			section.setMtime(new Date());
			this.update(section);
		}
	}

	@Override
	public Section getSectionByCode(String code) {
		return sectionRepo.getSectionByCode(code);
	}
	
	@Override
	public Section getSectionByName(String name) {
		return sectionRepo.getSectionByName(name);
	}

	@Override
	public Page<Section> getPagedSection(Integer start, Integer length, String orderName, String orderDir,
			String searchValue) {
		Sort sort = new Sort(Direction.DESC, "ctime");
		if (StringUtils.isNotEmpty(orderName) && StringUtils.isNotEmpty(orderDir)) {
			if ("asc".equalsIgnoreCase(orderDir)) {
				sort = new Sort(Direction.ASC, orderName);
			} else if ("desc".equalsIgnoreCase(orderDir)) {
				sort = new Sort(Direction.DESC, orderName);
			}
		}

		int pageNo = (start / length) > 0 ? (start / length) - 1 : 0;
		Pageable pageable = new PageRequest(pageNo, length, sort);
		Specification<Section> spc = this.getSearchSpecification(searchValue);

		Page<Section> page = sectionRepo.findAll(spc, pageable);
		return page;
	}

	private Specification<Section> getSearchSpecification(final String searchValue) {
		return new Specification<Section>() {
			@Override
			public Predicate toPredicate(Root<Section> paramRoot, CriteriaQuery<?> paramCriteriaQuery,
					CriteriaBuilder paramCriteriaBuilder) {

				List<Predicate> list = new ArrayList<Predicate>();

				list.add(paramCriteriaBuilder.ge(paramRoot.get("status").as(Number.class), 0));
				if (StringUtils.isNoneEmpty(searchValue))
					list.add(
							paramCriteriaBuilder.like(paramRoot.get("name").as(String.class), "%" + searchValue + "%"));
				Predicate[] p = new Predicate[list.size()];
				return paramCriteriaBuilder.and(list.toArray(p));
			}
		};
	}

}
