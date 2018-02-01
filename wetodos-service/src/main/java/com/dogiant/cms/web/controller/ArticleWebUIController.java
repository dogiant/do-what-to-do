package com.dogiant.cms.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogiant.cms.config.ImageConfig;
import com.dogiant.cms.domain.website.ArticleCat;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.service.ArticleCatService;
import com.dogiant.cms.service.MessageService;

@Controller
public class ArticleWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ArticleCatService articleCatService;
	
	@Autowired
	private MessageService messageService;
	
	@RequestMapping(value = "/article_cat_input", method = RequestMethod.GET)
    public String articleCatInput(Map<String, Object> model) {
		logger.info("/article_cat_input");
		
		List<ArticleCat> articleCats = articleCatService.getArticleCatSortList();
		model.put("articleCats", articleCats);
		model.put("menu", "article");
        return "article_cat_input";
    }
	
	@RequestMapping(value = "/article_cat_list", method = RequestMethod.GET)
    public String articleCatList(Map<String, Object> model) {
		logger.info("/article_cat_list");
		
		List<ArticleCat> articleCats = articleCatService.getArticleCatSortList();
		model.put("articleCats", articleCats);
		model.put("menu", "article");
        return "article_cat_list";
    }
	
	@RequestMapping(value = "/article_cat_modify", method = RequestMethod.GET)
    public String articleCatModify(@RequestParam(value = "catId", required = true) Long catId, Map<String, Object> model) {
		logger.info("/article_cat_modify");
		List<ArticleCat> articleCats = articleCatService.getArticleCatSortList();
		model.put("articleCats", articleCats);
		ArticleCat articleCat = articleCatService.getArticleCat(catId);
		model.put("articleCat", articleCat);
		model.put("menu", "article");
        return "article_cat_modify";
    }
	
	
	@RequestMapping(value = "/article_input", method = RequestMethod.GET)
    public String articleInput(Map<String, Object> model) {
		logger.info("/article_input");
		model.put("fileHost", ImageConfig.fileHost);
		
		List<ArticleCat> articleCats = articleCatService.getArticleCatSortList();
		model.put("articleCats", articleCats);
		model.put("menu", "article");
        return "article_input";
    }
	
	@RequestMapping(value = "/article_modify", method = RequestMethod.GET)
    public String articleModify(@RequestParam(value = "id", required = true) Long id,Map<String, Object> model) {
		logger.info("/article_modify");
		model.put("fileHost", ImageConfig.fileHost);
		
		List<ArticleCat> articleCats = articleCatService.getArticleCatSortList();
		model.put("articleCats", articleCats);
		
		ArticleItem articleItem = messageService.getArticleItem(id);
		model.put("articleItem", articleItem);
		
		model.put("menu", "article");
        return "article_modify";
    }
	
	@RequestMapping(value = "/article_preview", method = RequestMethod.GET)
    public String articlePreview(@RequestParam(value = "id", required = true) Long id,Map<String, Object> model) {
		logger.info("/article_preview");
		model.put("fileHost", ImageConfig.fileHost);

		ArticleItem articleItem = messageService.getArticleItem(id);
		model.put("articleItem", articleItem);
		
        return "article_preview";
    }
	
	@RequestMapping(value = "/article_list", method = RequestMethod.GET)
    public String articleList(Map<String, Object> model) {
		logger.info("/article_list");
		
		model.put("menu", "article");
        return "article_list";
    }
}
