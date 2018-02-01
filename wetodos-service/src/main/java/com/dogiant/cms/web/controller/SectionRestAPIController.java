package com.dogiant.cms.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.domain.dto.ServiceResponse;
import com.dogiant.cms.domain.dto.ServiceResponse2HttpResult;
import com.dogiant.cms.domain.website.ArticleCat;
import com.dogiant.cms.domain.website.Section;
import com.dogiant.cms.exception.CommException;
import com.dogiant.cms.exception.ServiceExInfo;
import com.dogiant.cms.service.SectionService;
import com.dogiant.cms.utils.QueryStringParser;

@RestController
public class SectionRestAPIController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private SectionService sectionService;
	
	@ResponseBody
	@RequestMapping(value = "/api/section/add", method = RequestMethod.POST)
	public HttpResult<?> sectionAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Section section) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			Date now = new Date();
			section.setCtime(now);
			section.setMtime(now);
			section.setStatus(0);
			System.out.println(section.getContent());
			sectionService.addSection(section);
		} catch (Exception e) {
			e.printStackTrace();
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/section/checkCodeExists", method = RequestMethod.POST)
	public String checkCodeExists(@RequestParam(value = "code", required = true) String code,
			HttpServletRequest request, HttpServletResponse response) {

		// 指定输出内容类型和编码
		response.setContentType("text/html;charset=utf-8");
		// 获取输出流，然后使用
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean returnval = true;
		Section section = null;
		if (StringUtils.isNotBlank(code)) {
			section = sectionService.getSectionByCode(code);
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || section != null) {
				out.print("false");
			} else {
				out.print("true");
			}
			out.flush();
			out.close();
		} catch (Exception ex) {
			out.println(ex.toString());
		}
		// 说明:因函数返回类型为void类型,即可不用返回，直接输出；
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/section/checkNameExists", method = RequestMethod.POST)
	public String checkNameExists(@RequestParam(value = "name", required = true) String name,
			HttpServletRequest request, HttpServletResponse response) {

		// 指定输出内容类型和编码
		response.setContentType("text/html;charset=utf-8");
		// 获取输出流，然后使用
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean returnval = true;
		Section section = null;
		if (StringUtils.isNotBlank(name)) {
			section = sectionService.getSectionByName(name);
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || section != null) {
				out.print("false");
			} else {
				out.print("true");
			}
			out.flush();
			out.close();
		} catch (Exception ex) {
			out.println(ex.toString());
		}
		// 说明:因函数返回类型为void类型,即可不用返回，直接输出；
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/section/update", method = RequestMethod.POST)
	public HttpResult<?> sectionUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Section section) throws CommException {
		
		if (section.getId() == null) {
			throw new CommException(ServiceExInfo.PARAMETER_ERROR_EXCEPTION);
		}
		
		Section sectionFromDB = sectionService.getSection(section.getId());
		
		if (sectionFromDB == null) {
			throw new CommException(ServiceExInfo.PARAMETER_ERROR_EXCEPTION);
		}
		
		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			Date now = new Date();
			section.setCtime(sectionFromDB.getCtime());
			section.setStatus(0);
			section.setMtime(now);
			sectionService.updateSection(section);
		} catch (Exception e) {
			e.printStackTrace();
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}

	@ResponseBody
	@RequestMapping(value = "/api/section/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> sectionDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<ArticleCat>> resp = ServiceResponse.successResponse();
		if(ids!=null && ids.length==1){
			Section section = sectionService.getSection(ids[0]);
			if (section.getType() == 0 && !"admin".equals(request.getAttribute("userName"))) {
				resp = resp.setCode(ServiceExInfo.NO_AUTH_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.NO_AUTH_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		}
		try {
			sectionService.deleteSections(ids);
		} catch (Exception e) {
			e.printStackTrace();
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/api/section/list", method = { RequestMethod.POST, RequestMethod.GET })
	public DataTablesResult<Section> sectionList(HttpServletRequest request, HttpServletResponse response) {
		
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
			orderName = params.get("columns["+ orderColumn +"][data]");
		}
		
		if (draw == null || start == null || length == null) {
			return null;
		}
		
		try {
			DataTablesResult<Section> dataTablesResult = sectionService.getSectionDataTablesResult(start, length, orderName, orderDir, searchValue);
			dataTablesResult.setDraw(draw);
			return dataTablesResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
