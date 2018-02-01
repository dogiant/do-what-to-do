package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dogiant.cms.domain.website.News;

public interface NewsRepo extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

}
