package com.dogiant.cms.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.ArticleItem;

public interface ArticleItemDao {

	void save(ArticleItem articleItem);

	ArticleItem getArticleItem(Long id);

	QueryResult<ArticleItem> getArticleItemQueryResult(Integer start, Integer length,
			String orderName, String orderDir, String searchValue);
	
	Page<ArticleItem> getPagedArticleItem(Integer pageNo, Integer pageRows, String catCode);
	
	ArticleItem getArticleItemValidDataById(Long id);

	List<ArticleItem> getLatestPost(List<String> catCodes, int size);
	
	List<ArticleItem> getRecommendItems(List<String> catCodes, int size);

}
