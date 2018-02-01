package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.website.NewsArticleItem;

public interface NewsArticleItemRepo extends JpaRepository<NewsArticleItem, Long>, JpaSpecificationExecutor<NewsArticleItem> {

	@Transactional(readOnly = true)
	@Query("select o from NewsArticleItem o where o.articleItem.id =:id")
	List<NewsArticleItem> getListByArticleItem(@Param("id")Long id);

}
