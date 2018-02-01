package com.dogiant.cms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.VideoDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.website.Video;
import com.dogiant.cms.service.VideoService;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

	@Resource
	VideoDao videoDao;
	
    SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public void addVideo(Video video) {

		videoDao.save(video);

	}

	@Override
	public void deleteVideo(Video video) {
		videoDao.delete(video);

	}

	@Override
	public List<Video> getVideoList(Video video) {
	    List<Video> list = new ArrayList<Video>();
	    list = videoDao.getVideoList(video);
		return list;
	}
	
	@Override
	public DataTablesResult<Video> getVideoDataTablesResult(Integer start, Integer length, String orderName,
			String orderDir, String searchValue) {
		
		QueryResult<Video> queryResult = this.getVideoQueryResult(start, length, orderName, orderDir, searchValue);
		if (queryResult.getResult() != null) {
			for(Video video : queryResult.getResult()){
				video.setCtimeStr(format.format(video.getCtime()));
			}
		}
		
		DataTablesResult<Video> dataTableResult = new DataTablesResult<Video>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

	public QueryResult<Video> getVideoQueryResult(
			Integer start, Integer length, String orderName, String orderDir, String searchValue) {
		return videoDao.getVideoQueryResult(start,length,orderName,orderDir,searchValue);
	}

}
