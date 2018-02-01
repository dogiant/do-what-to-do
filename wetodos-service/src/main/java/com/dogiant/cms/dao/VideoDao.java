package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.domain.website.Video;

public interface VideoDao {

	void save(Video video);
	
	void delete(Video video);
	
	List<Video> getVideoList(Video video);
	
	QueryResult<Video> getVideoQueryResult(Integer start, Integer length,
			String orderName, String orderDir, String searchValue);
}
