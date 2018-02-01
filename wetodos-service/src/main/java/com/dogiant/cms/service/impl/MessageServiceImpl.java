package com.dogiant.cms.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ArticleItemDao;
import com.dogiant.cms.dao.NewsArticleItemDao;
import com.dogiant.cms.dao.NewsDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.domain.website.MsgType;
import com.dogiant.cms.domain.website.News;
import com.dogiant.cms.domain.website.NewsArticleItem;
import com.dogiant.cms.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	private ArticleItemDao articleItemDao;
	
	@Autowired
	private NewsDao newsDao;
	
	@Autowired
	private NewsArticleItemDao newsArticleItemDao;

	@Override
	public void addSingleNews(ArticleItem articleItem) {
		
		System.out.println(articleItem);
		this.saveArticleItem(articleItem);

		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		Date now = c.getTime();
		
		// 创建一条单图文消息
		News news = new News();
		news.setCtime(now);
		news.setMsgType(MsgType.NEWS.getType());
		news.setMtime(now);
		news.setShare(false);
		news.setStatus(articleItem.getStatus());
		news.setUserName(articleItem.getUserName());
		this.saveNews(news);

		// 创建一条图文消息关联
		NewsArticleItem newsArticleItem = new NewsArticleItem();
		newsArticleItem.setArticleItem(articleItem);
		newsArticleItem.setSort(0);
		newsArticleItem.setNews(news);
		this.addNewsArticleItem(newsArticleItem);
		
	}

	private void addNewsArticleItem(NewsArticleItem newsArticleItem) {
		newsArticleItemDao.save(newsArticleItem);
	}

	private void saveNews(News news) {
		newsDao.save(news);
	}

	private void saveArticleItem(ArticleItem articleItem) {
		articleItemDao.save(articleItem);
		//此处可以处理存储后逻辑
	}

	
	/**
	 * 更新图文消息状态 1审核通过 -2自主删除 -3强制删除
	 * 
	 * @param ids
	 * @param status
	 */
	@Override
	public void updateArticleItem(Long[] ids, int status) {
		for (Long id : ids) {
			ArticleItem articleItem = this.getArticleItem(id);
			articleItem.setStatus(status);
			articleItem.setMtime(new Date());
			this.updateArticleItem(articleItem);
		}
	}

	@Override
	public ArticleItem getArticleItem(Long id) {
		return articleItemDao.getArticleItem(id);
	}

	/**
	 * 修改图文消息
	 * 
	 * @param articleItem
	 */
	@Override
	public void updateArticleItem(ArticleItem articleItem) {
		this.saveArticleItem(articleItem);
		// 更新news的状态
		List<NewsArticleItem> newsArticleItems = newsArticleItemDao.getListByArticleItem(articleItem.getId());
		for (NewsArticleItem item : newsArticleItems) {
			News news = item.getNews();
			news.setStatus(articleItem.getStatus());
			news.setMtime(new Date());
			this.saveNews(news);
		}
	}
	

	@Override
	public QueryResult<ArticleItem> getArticleItemQueryResult(
			Integer start, Integer length, String orderName, String orderDir, String searchValue) {
		return articleItemDao.getArticleItemQueryResult(start,length,orderName,orderDir,searchValue);
	}

	@Override
	public DataTablesResult<ArticleItem> getArticleItemDataTablesResult(Integer start, Integer length, String orderName,
			String orderDir, String searchValue) {
		
		QueryResult<ArticleItem> queryResult = this.getArticleItemQueryResult(start, length, orderName, orderDir, searchValue);
		//此处可处理成无需获取父子目录相关
		if (queryResult.getResult() != null) {
			for(ArticleItem articleItem : queryResult.getResult()){
				articleItem.setArticleCat(null);
			}
		}
		
		DataTablesResult<ArticleItem> dataTableResult = new DataTablesResult<ArticleItem>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

	
}
