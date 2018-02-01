package com.dogiant.cms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.NewsArticleItemDao;
import com.dogiant.cms.domain.website.NewsArticleItem;
import com.dogiant.cms.repo.NewsArticleItemRepo;

@Service("newsArticleItemDao")
public class NewsArticleItemDaoImpl implements NewsArticleItemDao {
	
	@Autowired
	private NewsArticleItemRepo newsArticleItemRepo;

	@Override
	public void save(NewsArticleItem newsArticleItem) {
		newsArticleItemRepo.save(newsArticleItem);
	}

	@Override
	public List<NewsArticleItem> getListByArticleItem(Long id) {
		return newsArticleItemRepo.getListByArticleItem(id);
	}

}
