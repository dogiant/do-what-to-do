package com.dogiant.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.website.Video;

public interface VideoRepo extends JpaRepository<Video, Long>, JpaSpecificationExecutor<Video> {


	@Transactional(readOnly = true)
	@Query("select o from Video o")
	List<Video> getVideoList();

}
