package com.dogiant.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.SectionHistoryDao;
import com.dogiant.cms.domain.website.SectionHistory;
import com.dogiant.cms.repo.SectionHistoryRepo;

@Service("sectionHistoryDao")
public class SectionHistoryDaoImpl implements SectionHistoryDao {

	@Autowired
	private SectionHistoryRepo sectionHistoryRepo;

	@Override
	public void add(SectionHistory sectionHistory) {
		sectionHistoryRepo.save(sectionHistory);
	}

}
