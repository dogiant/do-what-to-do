package com.dogiant.cms.domain.website;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dogiant.cms.config.ImageConfig;

/**
 * 
 * 图文消息
 * 
 * 
 */
@Entity
@Table(name = "news")
public class News extends Message {

	private Set<NewsArticleItem> newsAticles = new TreeSet<NewsArticleItem>();

	@OneToMany(cascade = { CascadeType.REFRESH }, mappedBy = "news", fetch = FetchType.EAGER)
	public Set<NewsArticleItem> getNewsAticles() {
		return newsAticles;
	}

	public void setNewsAticles(Set<NewsArticleItem> newsAticles) {
		this.newsAticles = newsAticles;
	}

	@Transient
	public String getMessageShow() {
		StringBuffer sb = new StringBuffer();
		if (newsAticles == null) {
			return null;
		}
		if (newsAticles.size() == 1) {
			Iterator<NewsArticleItem> it = newsAticles.iterator();
			NewsArticleItem newsArticleItem = null;
			while (it.hasNext()) {
				newsArticleItem = it.next();
			}
			ArticleItem articleItem = null;
			if (newsArticleItem != null) {
				articleItem = newsArticleItem.getArticleItem();
			}
			if (articleItem != null) {
				sb.append("<div class=\"thumbnail\" id=\"news_thumbnail\">");
				sb.append("<h4 id=\"news_title\" >" + articleItem.getTitle() + "</h4>");
				sb.append("<div id=\"cover_wrapper\">");
				sb.append("<img src=\"" + ImageConfig.imageUrlPrefix + articleItem.getTopCover()
						+ "\" id=\"news_cover\"  onerror=\"this.style.display='none'\" >");
				sb.append("<i>封面图片</i>");
				sb.append("</div>");
				sb.append("<div class=\"caption\">");
				sb.append("<p id=\"news_digest\" >");
				sb.append(articleItem.getDigest());
				sb.append("</p>");
				sb.append("<hr>");
				sb.append("<p>");
				sb.append("<a  href=\"javascript:void(0);\" onclick=\"openView(" + articleItem.getId()
						+ "); return false;\">阅读全文 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &gt;</a>");
				sb.append("</p>");
				sb.append("</div>");
				sb.append("</div>");
			}
		} else {
			NewsArticleItem newsArticleItem = null;
			Iterator<NewsArticleItem> it = newsAticles.iterator();
			int i = 1;
			sb.append("<div class=\"media_preview_area\">");
			sb.append("<div class=\"appmsg multi editing\">");
			sb.append("<div id=\"js_appmsg_preview\" class=\"appmsg_content\">");
			while (it.hasNext()) {
				newsArticleItem = it.next();
				ArticleItem articleItem = null;
				if (newsArticleItem != null) {
					articleItem = newsArticleItem.getArticleItem();
				}
				if (i == 1) {
					sb.append("<div id=\"appmsgItem1\" data-fileid=\"\" data-id=\"1\" class=\"js_appmsg_item\">");
					sb.append("<div class=\"appmsg_info\">");
					sb.append("<em class=\"appmsg_date\"></em>");
					sb.append("</div>");
					sb.append("<div class=\"cover_appmsg_item\">");
					sb.append("<h4 class=\"appmsg_title\"><a href=\"javascript:void(0);\" onclick=\"openView("
							+ articleItem.getId() + "); return false;\" target=\"_blank\">" + articleItem.getTitle()
							+ "</a></h4>");
					sb.append("<div class=\"appmsg_thumb_wrp\">");
					sb.append("<img class=\"js_appmsg_thumb appmsg_thumb\" src=\"" + ImageConfig.imageUrlPrefix
							+ articleItem.getTopCover() + "\"  style=\"display:block\">");
					if (StringUtils.isEmpty(articleItem.getTopCover())) {
						sb.append("<i class=\"appmsg_thumb default\">封面图片</i>");
					}
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
				} else {
					sb.append(
							"<div id=\"appmsgItem2\" data-fileid=\"\" data-id=\"2\" class=\"appmsg_item js_appmsg_item \">");
					sb.append("<img class=\"js_appmsg_thumb appmsg_thumb\" src=\"" + ImageConfig.imageUrlPrefix
							+ articleItem.getNormalThumbnail() + "\" style=\"display:block\">");
					if (StringUtils.isEmpty(articleItem.getNormalThumbnail())) {
						sb.append("<i class=\"appmsg_thumb default\">缩略图</i>");
					}
					sb.append("<h4 class=\"appmsg_title\"><a onclick=\"openView(" + articleItem.getId()
							+ "); return false;\" href=\"javascript:void(0);\" target=\"_blank\">"
							+ articleItem.getAdaptiveTitle() + "</a></h4>");
					sb.append("</div>");
				}
				i++;
			}
			sb.append("</div>");
			sb.append("</div>");
			sb.append("</div>");
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}