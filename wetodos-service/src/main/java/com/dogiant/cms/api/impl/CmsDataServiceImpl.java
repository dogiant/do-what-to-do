package com.dogiant.cms.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.dogiant.api.CmsDataService;
import com.dogiant.api.dto.ArticleCatDTO;
import com.dogiant.api.dto.ArticleItemDTO;
import com.dogiant.api.dto.PagedResult;
import com.dogiant.api.dto.SectionDTO;
import com.dogiant.cms.domain.website.ArticleCat;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.domain.website.Section;
import com.dogiant.cms.service.ArticleCatService;
import com.dogiant.cms.service.ArticleItemService;
import com.dogiant.cms.service.SectionService;

@Service("cmsDataService")
public class CmsDataServiceImpl implements CmsDataService {
	
	@Autowired
	private SectionService sectionService;
	
	@Autowired
	private ArticleCatService articleCatService;
	
	@Autowired
	private ArticleItemService articleItemService;

	@Override
	public String hello(String hi) {
		return "hello";
	}

	@Override
	public SectionDTO getSectionByCode(String code) {
		Section section = sectionService.getSectionByCode(code);
		if (section != null) {
			SectionDTO dto = new SectionDTO();
			try {
				BeanUtils.copyProperties(section, dto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			return dto;
		}
		return null;
	}

	@Override
	public ArticleCatDTO getArticleCatByCode(String code) {
		
		ArticleCat articleCat = articleCatService.getArticleCatByCatCode(code);
		if (articleCat != null) {
			ArticleCatDTO dto = new ArticleCatDTO();
			try {
				BeanUtils.copyProperties(articleCat, dto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			return dto;
		}
		return null;
	}

	@Override
	public List<ArticleCatDTO> getCrumbsArticleCats(String code) {
		List<ArticleCat> parents = articleCatService.getCrumbsArticleCats(code);
		if (CollectionUtils.isNotEmpty(parents)) {
			List<ArticleCatDTO> list = new ArrayList<ArticleCatDTO>();
			for (ArticleCat articleCat : parents) {
				ArticleCatDTO dto = new ArticleCatDTO();
				try {
					BeanUtils.copyProperties(articleCat, dto);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				list.add(dto);
			}
			return list;
		}
		return null;
	}

	@Override
	public ArticleItemDTO getArticleItemByCatCode(String code) {
		ArticleItem articleItem = articleItemService.getArticleItemByCatCode(code);
		if (articleItem != null) {
			ArticleItemDTO dto = new ArticleItemDTO();
			try {
				BeanUtils.copyProperties(articleItem, dto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			return dto;
		}
		return null;
	}

	@Override
	public List<ArticleItemDTO> getLatestPost(List<String> catCodes,int size) {
		List<ArticleItem> list = articleItemService.getLatestPost(catCodes, size);
		if(CollectionUtils.isNotEmpty(list)){
			List<ArticleItemDTO> dtoList= new ArrayList<ArticleItemDTO>();
			for(ArticleItem articleItem: list){
				ArticleItemDTO dto = new ArticleItemDTO();
				try {
					BeanUtils.copyProperties(articleItem, dto);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}

	@Override
	public PagedResult<ArticleItemDTO> getArticleItemsByCatCode(String code,Integer pageNo,Integer pageRows) {
		Page<ArticleItem> page = articleItemService.getPagedArticleItem(code, pageNo, pageRows);
		if (page != null && CollectionUtils.isNotEmpty(page.getContent())) {
			List<ArticleItemDTO> dtoList = new ArrayList<ArticleItemDTO>();
			for (ArticleItem articleItem : page.getContent()) {
				ArticleItemDTO dto = new ArticleItemDTO();
				try {
					BeanUtils.copyProperties(articleItem, dto);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				dtoList.add(dto);
			}
			PagedResult<ArticleItemDTO> pagedResult = new PagedResult<ArticleItemDTO>();
			return pagedResult.getPagedInfo(pageNo, pageRows, page.getTotalElements(), dtoList);
		}
		return null;
	}

	@Override
	public ArticleItemDTO getArticleItemById(Long id) {
		ArticleItem articleItem = articleItemService.getArticleItemValidDataById(id);
		if (articleItem != null) {
			ArticleItemDTO dto = new ArticleItemDTO();
			try {
				BeanUtils.copyProperties(articleItem, dto);
			} catch (BeansException e) {
				e.printStackTrace();
			}
			ArticleCat articleCat = articleItem.getArticleCat();
			if (articleCat != null) {
				ArticleCatDTO articleCatDTO = new ArticleCatDTO();
				try {
					BeanUtils.copyProperties(articleCat, articleCatDTO);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				dto.setArticleCatDTO(articleCatDTO);
			}
			return dto;
		}
		return null;
	}
	
	@Override
	public List<ArticleItemDTO> getRecommendItems(List<String> catCodes, int size){
		List<ArticleItem> list = articleItemService.getRecommendItems(catCodes, size);
		if(CollectionUtils.isNotEmpty(list)){
			List<ArticleItemDTO> dtoList= new ArrayList<ArticleItemDTO>();
			for(ArticleItem articleItem: list){
				ArticleItemDTO dto = new ArticleItemDTO();
				try {
					BeanUtils.copyProperties(articleItem, dto);
				} catch (BeansException e) {
					e.printStackTrace();
				}
				dtoList.add(dto);
			}
			return dtoList;
		}
		return null;
	}


	
}
