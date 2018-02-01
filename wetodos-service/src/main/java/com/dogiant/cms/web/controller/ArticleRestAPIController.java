package com.dogiant.cms.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.dogiant.cms.domain.website.ArticleItem;
import com.dogiant.cms.exception.CommException;
import com.dogiant.cms.exception.ServiceExInfo;
import com.dogiant.cms.service.ArticleCatService;
import com.dogiant.cms.service.MessageService;
import com.dogiant.cms.utils.QueryStringParser;

@RestController
public class ArticleRestAPIController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private ArticleCatService articleCatService;

	@Autowired
	private MessageService messageService;

	@ResponseBody
	@RequestMapping(value = "/api/article/cat/add", method = RequestMethod.POST)
	public HttpResult<?> articleCatAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ArticleCat articleCat) {

		// 如果顶级分类,则设定父分类为空，避免报错。
		if (articleCat != null && articleCat.getParent().getCatId() == null) {
			articleCat.setParent(null);
		}
		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			Date now = new Date();
			articleCat.setCtime(now);
			articleCat.setMtime(now);
			articleCatService.addArticleCat(articleCat);
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
	@RequestMapping(value = "/api/article/cat/checkSameLevelCatCodeExists", method = RequestMethod.POST)
	public String checkCodeExists(@RequestParam(value = "parentCatId", required = true) Long parentCatId,
			@RequestParam(value = "catId", required = false) Long catId,
			@RequestParam(value = "catCode", required = true) String catCode, HttpServletRequest request,
			HttpServletResponse response) {

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
		ArticleCat articleCat = null;
		if (StringUtils.isNotBlank(catCode)) {
			try {
				articleCat = articleCatService.checkAllLevelCatCodeExists(parentCatId, catCode);
			} catch (Exception e) {
				e.printStackTrace();
				returnval = false;
			}
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || articleCat != null) {
				if (catId != null && articleCat!=null && articleCat.getCatId().intValue()==catId.intValue()) {
					out.print("true");
				}else{
					out.print("false");
				}
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
	@RequestMapping(value = "/api/article/cat/checkSameLevelCatNameExists", method = RequestMethod.POST)
	public String checkNameExists(@RequestParam(value = "parentCatId", required = true) Long parentCatId,
			@RequestParam(value = "catId", required = false) Long catId,
			@RequestParam(value = "catName", required = true) String catName, HttpServletRequest request,
			HttpServletResponse response) {

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
		ArticleCat articleCat = null;
		if (StringUtils.isNotBlank(catName)) {
			try {
				articleCat = articleCatService.checkAllLevelCatNameExists(parentCatId, catName);
			} catch (Exception e) {
				e.printStackTrace();
				returnval = false;
			}
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || articleCat != null) {
				if (catId != null && articleCat!=null && articleCat.getCatId().intValue()==catId.intValue()) {
					out.print("true");
				}else{
					out.print("false");
				}
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
	@RequestMapping(value = "/api/article/cat/update", method = RequestMethod.POST)
	public HttpResult<?> articleCatUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ArticleCat articleCat) throws CommException {

		if (articleCat.getCatId() == null) {
			throw new CommException(ServiceExInfo.PARAMETER_ERROR_EXCEPTION);
		}

		ArticleCat articleCatFromDB = articleCatService.getArticleCat(articleCat.getCatId());

		if (articleCatFromDB == null) {
			throw new CommException(ServiceExInfo.PARAMETER_ERROR_EXCEPTION);
		}

		// 父级分类不能是自己
		if (articleCat != null && articleCat.getParent().getCatId() == articleCat.getCatId()) {
			articleCat.setParent(null);
		}

		// 如果顶级分类,则设定父分类为空，避免报错。
		if (articleCat != null && articleCat.getParent().getCatId() == null) {
			articleCat.setParent(null);
		}

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			Date now = new Date();
			articleCat.setCtime(articleCatFromDB.getCtime());
			articleCat.setMtime(now);
			articleCatService.addArticleCat(articleCat);
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
	@RequestMapping(value = "/api/article/cat/delete", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> articleCatList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<ArticleCat>> resp = ServiceResponse.successResponse();
		
		if (ids != null && ids.length == 1) {
			ArticleCat articleCat = articleCatService.getArticleCat(ids[0]);
			if (articleCat.getCatType() == 0 && !"admin".equals(request.getAttribute("userName"))) {
				resp = resp.setCode(ServiceExInfo.NO_AUTH_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.NO_AUTH_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		}
		try {
			articleCatService.deleteArticleCats(ids);
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
	@RequestMapping(value = "/api/article/cat/list", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> articleCatList(HttpServletRequest request, HttpServletResponse response) {

		ServiceResponse<List<ArticleCat>> resp = ServiceResponse.successResponse();
		try {
			List<ArticleCat> list = articleCatService.getArticleCatSortList();
			resp.setData(list);
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
	@RequestMapping(value = "/api/article/add", method = RequestMethod.POST)
	public HttpResult<?> articleAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ArticleItem articleItem) {

		if (articleItem.getArticleCat() == null || articleItem.getArticleCat().getCatId() == null) {
			articleItem.setArticleCat(null);
		}

		Date now = new Date();
		articleItem.setCtime(now);
		articleItem.setMtime(now);
		articleItem.setUserName((String) request.getAttribute("userName"));
		// 0 先发后审 -1先审后发
		articleItem.setStatus(0);

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			// 此处方法里面有增加 news 及 newsArticleItem 逻辑
			messageService.addSingleNews(articleItem);
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
	@RequestMapping(value = "/api/article/delete", method = RequestMethod.POST)
	public HttpResult<?> articleDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				ArticleItem articleItem = messageService.getArticleItem(id);
				if (articleItem != null) {
					articleItem.setStatus(-3);
					messageService.updateArticleItem(articleItem);
				}
			} catch (Exception e) {
				e.printStackTrace();

				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}

	@ResponseBody
	@RequestMapping(value = "/api/article/update", method = RequestMethod.POST)
	public HttpResult<?> articleUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute ArticleItem articleItem) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();

		if (articleItem.getArticleCat() == null || articleItem.getArticleCat().getCatId() == null) {
			articleItem.setArticleCat(null);
		}

		ArticleItem aticleItemFromDB = messageService.getArticleItem(articleItem.getId());
		if (aticleItemFromDB == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		Date now = new Date();
		articleItem.setCtime(aticleItemFromDB.getCtime());
		articleItem.setMtime(now);
		articleItem.setUserName((String) request.getAttribute("userName"));
		// 0 先发后审 -1先审后发
		articleItem.setStatus(0);

		try {
			// 此处方法里面有增加 news 及 newsArticleItem 逻辑
			messageService.updateArticleItem(articleItem);
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
	@RequestMapping(value = "/api/article/enable", method = RequestMethod.POST)
	public HttpResult<?> articleReEnable(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				ArticleItem articleItem = messageService.getArticleItem(id);
				if (articleItem != null) {
					articleItem.setStatus(1);
					messageService.updateArticleItem(articleItem);
				}
			} catch (Exception e) {
				e.printStackTrace();

				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}

	@ResponseBody
	@RequestMapping(value = "/api/article/preview", method = RequestMethod.GET)
	public HttpResult<?> articlePreview(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) {

		ServiceResponse<ArticleItem> resp = ServiceResponse.successResponse();

		try {
			ArticleItem articleItem = messageService.getArticleItem(id);
			if (articleItem == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			resp.setData(articleItem);
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
	@RequestMapping(value = "/api/article/{id}", method = RequestMethod.GET)
	public HttpResult<?> articleView(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {

		ServiceResponse<ArticleItem> resp = ServiceResponse.successResponse();

		try {
			ArticleItem articleItem = messageService.getArticleItem(id);
			if (articleItem == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			if (articleItem.getStatus() < 0) {
				resp = resp.setCode(ServiceExInfo.NO_RESULT_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.NO_RESULT_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			resp.setData(articleItem);
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
	@RequestMapping(value = "/api/article/list", method = { RequestMethod.POST, RequestMethod.GET })
	public DataTablesResult<ArticleItem> articleList(HttpServletRequest request, HttpServletResponse response) {

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
			DataTablesResult<ArticleItem> dataTablesResult = messageService.getArticleItemDataTablesResult(start,
					length, orderName, orderDir, searchValue);
			dataTablesResult.setDraw(draw);
			return dataTablesResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
