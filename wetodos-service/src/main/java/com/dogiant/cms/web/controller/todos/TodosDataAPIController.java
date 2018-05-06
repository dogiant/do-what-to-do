package com.dogiant.cms.web.controller.todos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.domain.dto.PagedQuery;
import com.dogiant.cms.domain.dto.ServiceResponse;
import com.dogiant.cms.domain.dto.ServiceResponse2HttpResult;
import com.dogiant.cms.domain.todos.DailyBanner;
import com.dogiant.cms.domain.todos.LearningPlan;
import com.dogiant.cms.exception.ServiceExInfo;
import com.dogiant.cms.service.AnswerService;
import com.dogiant.cms.service.BookService;
import com.dogiant.cms.service.ChapterService;
import com.dogiant.cms.service.DailyBannerService;
import com.dogiant.cms.service.LearningPlanService;
import com.dogiant.cms.service.PhaseService;
import com.dogiant.cms.service.QuestionService;
import com.dogiant.cms.utils.HttpUtil;
import com.dogiant.cms.utils.WeChatUtil;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("/todos/data/api")
public class TodosDataAPIController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PhaseService phaseService;
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@Autowired
	private DailyBannerService dailyBannerService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private AnswerService answerService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOpenId", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public HttpResult<?> getOpenId(HttpServletRequest request, HttpServletResponse response, @RequestParam("scode")String scode) {
		
		String appId ="wxee2aaaf21711ce97";
		String appSecret = "3e93c6d0779e26904e0cdc69bd5acf0a";
		
		String requestUrl = String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code", appId, appSecret, scode);
		logger.info(requestUrl);
		
		ServiceResponse<Map<String,String>> resp = ServiceResponse.successResponse();
		
		try {
			Map<String,String> resultMap = new HashMap<String,String>();
			JSONObject jsonObject = WeChatUtil.httpRequest(requestUrl, "GET", null);
			if(jsonObject!=null){
				resultMap.put("openid", jsonObject.getString("openid"));
				resultMap.put("session_key", jsonObject.getString("session_key"));
			}
			resp.setData(resultMap);
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
	@RequestMapping(value = "/getDailyBanner", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public HttpResult<?> getDailyBanner(HttpServletRequest request, HttpServletResponse response) {
		ServiceResponse<DailyBanner> resp = ServiceResponse.successResponse();
		try {
			DailyBanner dailyBanner = dailyBannerService
					.getDailyBannerByDate(new SimpleDateFormat("yyyy-MM-dd")
							.format(new Date()));
			if (dailyBanner == null) {
				dailyBanner = dailyBannerService.getDailyBannerByDate("default");
			}
			resp.setData(dailyBanner);
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
	@RequestMapping(value = "/getUsersPlan", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public HttpResult<?> getUsersOngoingLearningPlan(HttpServletRequest request, HttpServletResponse response) {
		ServiceResponse<LearningPlan> resp = ServiceResponse.successResponse();
		try {
			
			resp.setData(null);
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
	@RequestMapping(value = "/getLearningPlans", method = {RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	public HttpResult<?> getLearningPlans(HttpServletRequest request, HttpServletResponse response, @RequestBody PagedQuery pagedQuery) {
		ServiceResponse<DataTablesResult<LearningPlan>> resp = ServiceResponse.successResponse();
		try {
			DataTablesResult<LearningPlan> result = learningPlanService.getLearningPlanDataTablesResultSimple(pagedQuery.getStart(), pagedQuery.getLength(), pagedQuery.getKeyword());
			resp.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}
	

}
