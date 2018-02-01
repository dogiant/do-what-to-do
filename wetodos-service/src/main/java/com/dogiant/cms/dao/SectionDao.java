package com.dogiant.cms.dao;

import org.springframework.data.domain.Page;

import com.dogiant.cms.domain.website.Section;

public interface SectionDao {

	void save(Section section);

	void update(Section section);

	Section getSection(Long id);
	
	void delete(Long id);

	Section getSectionByCode(String code);
	
	Section getSectionByName(String name);

	Page<Section> getPagedSection(Integer start,
			Integer length, String orderName, String orderDir,
			String searchValue);

}
