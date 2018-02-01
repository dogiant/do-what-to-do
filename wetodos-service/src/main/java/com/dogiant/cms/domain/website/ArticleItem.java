package com.dogiant.cms.domain.website;

import static javax.persistence.GenerationType.IDENTITY;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.dogiant.cms.config.ImageConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 图文条目
 * 
 * @author dogiant
 * 
 */
@Entity
@Table(name = "article_item")
public class ArticleItem {

	private Long id;
	private String userName;
	/**
	 * 文章栏目
	 */
	private ArticleCat articleCat;
	private String title;
	//副标题，用于排版非头条标题文字
	private String subtitle;
	private String author;
	private String digest;
	private String content;
	//正文：450px*277px  ，素材预览，314*193
	//450*250
	//大图片建议尺寸：360像素 * 200像素，小图片建议尺寸：200像素 * 200像素
	private String coverPicUrl;
	
	// 0source 1link 2file
	private Integer type;

	//原文链接
	private String sourceUrl;
	//标题跳转链接
	private String linkUrl;
	//文件下载地址
	private String fileUrl;
	
	//是否将封面放到正文中
	private Boolean coverIntoContent;
	
	// 是否推荐到首页
	private Boolean recommend;
	//0 先发后审 1审核通过 -1先审后发 -2自主删除 -3强制删除
	private Integer status;
	//创建时间
	private Date ctime;
	//修改时间
	private Date mtime;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "user_name", nullable = false, length = 60)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name="cat_id")
	public ArticleCat getArticleCat() {
		return articleCat;
	}

	public void setArticleCat(ArticleCat articleCat) {
		this.articleCat = articleCat;
	}

	@Column(name = "type", nullable = false)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "title", nullable = false, length = 64)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "subtitle", nullable = true, length = 64)
	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Transient
	public String getAdaptiveTitle(){
		if(StringUtils.isNotEmpty(this.subtitle)){
			return subtitle;
		}else{
			return title;
		}
	}
	
	@Column(name = "author", nullable = true, length = 32)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "digest", nullable = true, length = 1200)
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
	
	@Lob
	@Column(name = "content", nullable = true)
	public String getContent() {
		if(content!=null){
			content = content.replaceAll("(\r\n|\r|\n|\n\r)", "");
		}
		return content;
	}
	
	/**
	 * 得到封面和正文
	 * @return
	 */
	@Transient
	public String getCoverAndContent() {
		String cover = "<p><img src=\""+ this.getCoverPicUrl() +"\" style=\"float: none;\"><br></p>";
		return cover.concat(content);
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "cover_pic_url", nullable = true)
	public String getCoverPicUrl() {
		return coverPicUrl;
	}

	public void setCoverPicUrl(String coverPicUrl) {
		this.coverPicUrl = coverPicUrl;
	}
	
	/**
	 * 得到封面图片360*200
	 * @return
	 */
	@Transient
	public String getTopCover(){
		if(StringUtils.isNotEmpty(coverPicUrl)){
			return coverPicUrl.substring(0, coverPicUrl.lastIndexOf(".")) + coverPicUrl.substring(coverPicUrl.lastIndexOf("."));
		}
		return coverPicUrl;
	}
	
	/**
	 * 得到200*200缩略图
	 * @return
	 */
	@Transient
	public String getNormalThumbnail(){
		if(StringUtils.isNotEmpty(coverPicUrl)){
			return coverPicUrl.substring(0, coverPicUrl.lastIndexOf(".")) + coverPicUrl.substring(coverPicUrl.lastIndexOf("."));
		}
		return coverPicUrl;
	}

	@Column(name = "cover_into_content", nullable = true)
	public Boolean getCoverIntoContent() {
		return coverIntoContent;
	}

	public void setCoverIntoContent(Boolean coverIntoContent) {
		this.coverIntoContent = coverIntoContent;
	}
		
	@Column(name = "recommend", nullable = true)
	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	@Column(name = "file_url")
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Column(name = "source_url")
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	@Column(name = "link_url", nullable = true)
	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 返回状态描述
	 * @return
	 */
	@Transient
	public String getStatusDescription() {
		switch(status){
			case 0:
				return "正常显示";
			case 1:
				return "审核通过";
			case -1:
				return "等待审核";
			case -2:
				return "自主删除";
			case -3:
				return "强制删除";
			default:
				return "未知状态";
		}
	}
	
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	/**
	 * 发表时间
	 * @return
	 */
	@Transient
	public String getPubTime(){
		return new SimpleDateFormat("yy-MM-dd HH:mm").format(ctime);
	}

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "mtime")
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	@Transient
	public String getTypeDesc(){
		switch(type){
		case 0:
			return "普通文章";
		case 1:
			return "外部链接";
		case 2:
			return "附件文章";
		default:
			return "未知状态";
	}
	}

	@Transient
	public String getMessageShow() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"thumbnail\" id=\"news_thumbnail\">");
		sb.append("<h4 id=\"news_title\" >"+ getTitle() +"</h4>");
		sb.append("<div id=\"cover_wrapper\">");
		sb.append("<img src=\""+ImageConfig.imageUrlPrefix + getTopCover()+"\" id=\"news_cover\" onerror=\"this.style.display='none'\" />");
		sb.append("<i>封面图片</i>");
		sb.append("</div>");
		sb.append("<div class=\"caption\">");
		sb.append("<p id=\"news_digest\" >");
		sb.append(getDigest());
		sb.append("</p>");
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.DEFAULT_STYLE);
	}

}
