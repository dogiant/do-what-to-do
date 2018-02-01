package com.dogiant.cms.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.domain.website.Video;
import com.dogiant.cms.service.VideoService;
import com.dogiant.cms.utils.QueryStringParser;

@Controller
public class VideoWebUIController {
	
	@Resource
	VideoService videoService;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 视频上传
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadVedio", method = RequestMethod.GET)
	public String uploadVedio(Map<String, Object> model,HttpServletRequest request) {
		model.put("menu", "video");
        return "videoUpload";
	}
	
	/**
	 * 视频列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/videoList", method = RequestMethod.GET)
	public String videoList(Map<String, Object> model,HttpServletRequest request) {
		Video video = new Video();
		List<Video> list = new ArrayList<Video>();
		try {
			list = videoService.getVideoList(video);
		} catch (Exception e) {
			logger.info("查询视频列表异常："+e.getMessage());
		}
		model.put("menu", "video");
		model.put("videoList", list);
        return "video_list";
	}
	
	/**
	 * 视频保存
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveVideoInfo", method = RequestMethod.POST)
	public String saveVideoInfo(Map<String, Object> model,HttpServletRequest request) {
		String catId = request.getParameter("catId");
		String videoName = request.getParameter("videoName");
		String keywords = request.getParameter("keywords");
		String videoDesc = request.getParameter("videoDesc");
		String fileId = request.getParameter("fileId");
		String url = request.getParameter("url");

		Video video = new Video();
		video.setVideoName(videoName);
		video.setKeywords(keywords);
		video.setVideoDesc(videoDesc);
		video.setFileId(Long.parseLong(fileId));
		video.setUrl(url);
		try {
			videoService.addVideo(video);
		} catch (Exception e) {
			logger.info("保存视频失败："+e.getMessage());
			return "false";
		}
        return "true";
	}
	
	/**
	 * video list
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVideoList", method = { RequestMethod.POST, RequestMethod.GET })
	public DataTablesResult<Video> articleList(HttpServletRequest request, HttpServletResponse response) {

		String tranToken = request.getQueryString();

		Map<String, String> params = QueryStringParser.queryStringParser(tranToken, "utf-8");

		Integer draw = null;
		Integer start = null;
		Integer length = null;

		try {
			draw = Integer.parseInt(request.getParameter("draw"));
			start = Integer.parseInt(request.getParameter("start"));
			length = Integer.parseInt(request.getParameter("length"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String orderColumn = null;
		String orderName = null;
		String orderDir = null;
		String searchValue = null;

		orderColumn = params.get("order[0][column]");
		orderDir = params.get("order[0][dir]");
		searchValue = params.get("search[value]");
		if (StringUtils.isNotEmpty(orderColumn)) {
			orderName = params.get("columns[" + orderColumn + "][data]");
		}

		if (draw == null || start == null || length == null) {
			return null;
		}

		try {
			DataTablesResult<Video> dataTablesResult = videoService.getVideoDataTablesResult(start,
					length, orderName, orderDir, searchValue);
			dataTablesResult.setDraw(draw);
			return dataTablesResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
