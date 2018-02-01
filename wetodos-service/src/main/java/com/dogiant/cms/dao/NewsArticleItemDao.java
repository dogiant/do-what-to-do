package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.website.NewsArticleItem;

public interface NewsArticleItemDao {

	void save(NewsArticleItem newsArticleItem);

	List<NewsArticleItem> getListByArticleItem(Long id);

}
