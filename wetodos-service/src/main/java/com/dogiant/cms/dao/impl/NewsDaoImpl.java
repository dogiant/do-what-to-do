package com.dogiant.cms.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.NewsDao;
import com.dogiant.cms.domain.website.News;
import com.dogiant.cms.repo.NewsRepo;

@Service("newsDao")
public class NewsDaoImpl implements NewsDao {
	
	@Autowired
	private NewsRepo newsRepo;

	@Override
	public void save(News news) {
		newsRepo.save(news);
	}

}
