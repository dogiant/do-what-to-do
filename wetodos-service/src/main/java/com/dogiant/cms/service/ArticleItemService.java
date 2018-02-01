package com.dogiant.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dogiant.cms.domain.website.ArticleItem;

public interface ArticleItemService {
	
	public Page<ArticleItem> getPagedArticleItem(String catCode, int pageNo, int pageRows);
	
	public ArticleItem getArticleItemValidDataById(Long id);

	public ArticleItem getArticleItemByCatCode(String catCode);

	public List<ArticleItem> getLatestPost(List<String> catCodes, int size);
	
	public List<ArticleItem> getRecommendItems(List<String> catCodes,int size);
	
}
