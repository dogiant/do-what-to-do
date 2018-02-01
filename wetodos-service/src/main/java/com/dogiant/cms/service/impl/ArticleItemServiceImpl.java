package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ArticleItemDao;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.service.ArticleItemService;

@Service("articleItemService")
public class ArticleItemServiceImpl implements ArticleItemService {

	@Autowired
	private ArticleItemDao articleItemDao;

	@Override
	public Page<ArticleItem> getPagedArticleItem(
			String catCode, int pageNo, int pageRows) {
		return articleItemDao.getPagedArticleItem(pageNo, pageRows, catCode);
	}

	@Override
	public ArticleItem getArticleItemValidDataById(Long id) {
		return articleItemDao.getArticleItemValidDataById(id);
	}

	@Override
	public ArticleItem getArticleItemByCatCode(String catCode) {
		try {
			return articleItemDao.getPagedArticleItem(1, 1, catCode).getContent().get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ArticleItem> getLatestPost(List<String> catCodes, int size) {
		return articleItemDao.getLatestPost(catCodes, size);
	}
	
	@Override
	public List<ArticleItem> getRecommendItems(List<String> catCodes, int size) {
		return articleItemDao.getRecommendItems(catCodes, size);
	}

	
}
