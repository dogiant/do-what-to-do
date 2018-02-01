package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.website.ArticleCat;

public interface ArticleCatService {

	/**
	 * 创建普通栏目
	 * @param articleCat
	 */
	void addArticleCat(ArticleCat articleCat);

	/**
	 * 更新普通栏目
	 * @param articleCat
	 */
	void updateArticleCat(ArticleCat articleCat);

	/**
	 * 获取栏目信息
	 * @param id
	 * @return
	 */
	ArticleCat getArticleCat(Long id);

	/**
	 * 获取排序后栏目
	 * @param weChatId
	 * @return
	 */
	List<ArticleCat> getArticleCatSortList();

	/**
	 * 删除栏目
	 * @param ids
	 * @param weChatId
	 * @param isAdmin
	 * @throws CommException
	 */
	void deleteArticleCats(Long[] ids);

	/**
	 * 根据栏目代码获取栏目
	 * @param catCode
	 * @return
	 */
	ArticleCat getArticleCatByCatCode(String catCode);

	ArticleCat checkAllLevelCatNameExists(Long parentCatId, String catName);

	ArticleCat checkAllLevelCatCodeExists(Long parentCatId, String catCode);

	List<ArticleCat> getCrumbsArticleCats(String code);

}