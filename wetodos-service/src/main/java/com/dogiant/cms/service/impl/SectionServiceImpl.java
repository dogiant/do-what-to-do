package com.dogiant.cms.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.SectionDao;
import com.dogiant.cms.dao.SectionHistoryDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.website.Section;
import com.dogiant.cms.domain.website.SectionHistory;
import com.dogiant.cms.service.SectionService;

@Service("sectionService")
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionDao sectionDao;

	@Autowired
	private SectionHistoryDao sectionHistoryDao;

	@Override
	public Section getSection(Long id) {
		return sectionDao.getSection(id);
	}

	@Override
	public Section getSectionByCode(String code) {
		return sectionDao.getSectionByCode(code);
	}

	@Override
	public Section getSectionByName(String name) {
		return sectionDao.getSectionByName(name);
	}

	@Override
	public void addSection(Section section) {
		sectionDao.save(section);
		SectionHistory sectionHistory = new SectionHistory(section.getCode(), section.getName(), section.getContent(),
				new Date());
		sectionHistoryDao.add(sectionHistory);
	}

	@Override
	public void deleteSections(Long[] ids) {
		for (Long id : ids) {
			sectionDao.delete(id);
		}

	}

	@Override
	public void updateSection(Section section) {
		sectionDao.save(section);
		SectionHistory sectionHistory = new SectionHistory(section.getCode(), section.getName(), section.getContent(),
				new Date());
		sectionHistoryDao.add(sectionHistory);
	}

	@Override
	public DataTablesResult<Section> getSectionDataTablesResult(Integer start, Integer length, String orderName,
			String orderDir, String searchValue) {
		Page<Section> page = sectionDao.getPagedSection(start, length, orderName, orderDir, searchValue);

		DataTablesResult<Section> dataTablesResult = new DataTablesResult<Section>();
		dataTablesResult.setData(page.getContent());
		dataTablesResult.setRecordsTotal(page.getTotalElements());
		dataTablesResult.setRecordsFiltered(page.getTotalElements());
		return dataTablesResult;
	}

}
