package com.dogiant.cms.service;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.website.Section;

public interface SectionService {

	public Section getSection(Long id);

	public void addSection(Section section);

	public void deleteSections(Long[] ids);

	public void updateSection(Section section);

	public DataTablesResult<Section> getSectionDataTablesResult(Integer start, Integer length, String orderName,
			String orderDir, String searchValue);

	public Section getSectionByCode(String code);

	public Section getSectionByName(String name);

}
