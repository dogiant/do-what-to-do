package com.dogiant.cms.init;

import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.ServletContextAware;

import com.dogiant.cms.domain.admin.AdminUser;
import com.dogiant.cms.domain.website.ArticleCat;
import com.dogiant.cms.service.AdminUserService;
import com.dogiant.cms.service.ArticleCatService;

@Component
public class InitDataListener implements InitializingBean , ServletContextAware {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private ArticleCatService articleCatService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		System.out.println("setServletContext...");
		if (adminUserService.findUserByUserNameValid("admin") == null) {
			AdminUser adminUser = new AdminUser();
			adminUser.setUserName("admin");
			adminUser.setNickname("超级管理员");
			adminUser.setEmail("admin@feilongshiliugongge.com");
			adminUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
			adminUser.setPhone("13651277590");
			adminUser.setCtime(new Date());
			adminUser.setIsValid(1);
			adminUser.setMtime(new Date());
			adminUserService.saveOrUpdate(adminUser);
			System.out.println("add adminUser...");
		}
		if (articleCatService.getArticleCatByCatCode("news") == null) {
			Date now = new Date();
			//String catCode, String catName, int catType, Boolean isTextCat, String keywords, String catDesc,Integer sortOrder, Boolean showInNav, Date ctime, Date mtime
			ArticleCat education = new ArticleCat("education","书画教育",1,false,"书画,教育","书画教育",1,true,now,now);
			ArticleCat appreciation = new ArticleCat("appreciation","书画赏析",1,false,"书画,赏析","书画赏析",2,true,now,now);
			ArticleCat mall = new ArticleCat("mall","书画商城",1,false,"书画商城","书画商城",3,true,now,now);
			ArticleCat news = new ArticleCat("news","新闻中心",1,false,"新闻中心","新闻中心",4,true,now,now);
			ArticleCat aboutus = new ArticleCat("aboutus","关于我们",1,false,"关于我们","关于我们",5,true,now,now);
			
			
			//textbooks:  
			
			//author-team video school
			
			
			//video-teaching
//			regular 
//			jiuchenggong
//			yanqinglibei
//			duobaotabei
//			xuanmitabei
//			sanmenji

			
//			16copy
//		    calligraphy 
//			painting
//			tablet
//			picture
			
			//school
//			map
//			team
//			works
			
//			bietie
//			mingbie
//			mingtie
			
//			Chinese and foreign famous paintings
//			Contemporary painting
			
			
//			Calligraphy and painting supplies
//			books
//			Stationery
			
//			news
//			report
//			activity
//			notice
//			
//			
//			shiliugongge
//			joinus
		}
		
		//版块
		//十六宫格简介
		//视频教程
		//新闻中心
		//书画欣赏
		//合作伙伴
		//联系我们

		
		 
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet...");
	}

}
