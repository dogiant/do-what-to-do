package com.dogiant.cms.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ArticleCatDao;
import com.dogiant.cms.domain.website.ArticleCat;
import com.dogiant.cms.repo.ArticleCatRepo;

@Service("articleCatDao")
public class ArticleCatDaoImpl implements ArticleCatDao {
	
	@Autowired
	private ArticleCatRepo articleCatRepo;

	@Override
	public void save(ArticleCat articleCat) {
		articleCatRepo.save(articleCat);
	}

	@Override
	public void update(ArticleCat articleCat) {
		articleCatRepo.save(articleCat);
	}

	@Override
	public ArticleCat getArticleCat(Long catId) {
		return articleCatRepo.getOne(catId);
	}

	@Override
	public List<ArticleCat> getTopScrollData() {
		return articleCatRepo.getTopScrollData();
	}

	@Override
	public List<ArticleCat> getArticleCatChildrenList(Long catId) {
		return articleCatRepo.getArticleCatList(catId);
	}

	@Override
	public void delete(Long catId) {
		articleCatRepo.delete(catId);
	}

	@Override
	public ArticleCat getArticleCatByCatCode(String catCode) {
		return articleCatRepo.getArticleCatByCatCode(catCode);
	}
	
	@Override
	public ArticleCat getArticleCatByCatName(String catName) {
		return articleCatRepo.getArticleCatByCatName(catName);
	}

	@Override
	public ArticleCat checkAllLevelCatNameExists(Long parentCatId, String catName) {
		
		return this.getArticleCatByCatName(catName);
		
//		if(parentCatId==null){
//			return articleCatRepo.checkTopLevelCatNameExists(catName);
//		}
//		return articleCatRepo.checkSameLevelCatNameExists(parentCatId,catName);
	}

	@Override
	public ArticleCat checkAllLevelCatCodeExists(Long parentCatId, String catCode) {
		
		return this.getArticleCatByCatCode(catCode);
		
//		if(parentCatId==null){
//			articleCatRepo.checkTopLevelCatCodeExists(catCode);
//		}
//		return articleCatRepo.checkSameLevelCatCodeExists(parentCatId,catCode);
	}
	
}
