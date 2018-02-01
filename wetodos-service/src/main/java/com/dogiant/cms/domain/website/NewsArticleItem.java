package com.dogiant.cms.domain.website;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 新闻文章 辅助实体
 * 
 * 
 */
@Entity
@Table(name = "news_article_item")
public class NewsArticleItem {

	private Long id;

	private News news;

	private ArticleItem articleItem;

	private Integer sort;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "news_id")
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	@ManyToOne(cascade = CascadeType.REFRESH )
	@JoinColumn(name = "article_id")
	public ArticleItem getArticleItem() {
		return articleItem;
	}

	public void setArticleItem(ArticleItem articleItem) {
		this.articleItem = articleItem;
	}

	@Column(name = "sort_order", nullable = false)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}
}