package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.website.ArticleCat;

public interface ArticleCatDao {

	void save(ArticleCat articleCat);

	void update(ArticleCat articleCat);

	ArticleCat getArticleCat(Long catId);
	
	List<ArticleCat> getTopScrollData();

	List<ArticleCat> getArticleCatChildrenList(Long catId);
	
	void delete(Long id);

	ArticleCat getArticleCatByCatCode(String catCode);
	
	ArticleCat getArticleCatByCatName(String catName);

	ArticleCat checkAllLevelCatNameExists(Long parentCatId, String catName);

	ArticleCat checkAllLevelCatCodeExists(Long parentCatId, String catCode);

}
