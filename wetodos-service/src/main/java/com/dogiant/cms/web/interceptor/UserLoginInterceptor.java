package com.dogiant.cms.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.dogiant.cms.cookie.CookieUtil;
import com.dogiant.cms.domain.dto.BaseResponseCode;
import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.domain.dto.ServiceResponse;
import com.dogiant.cms.domain.dto.ServiceResponse2HttpResult;
import com.dogiant.cms.utils.OSCacheManager;
import com.dogiant.cms.utils.SendMsgUtil;

public class UserLoginInterceptor implements HandlerInterceptor {

	protected final static Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String sessionId = CookieUtil.getCookie(request, "sessionid");
		logger.info(sessionId);
		StringBuffer url = request.getRequestURL();
		logger.info(url.toString());
		String json = null;
		try {
			json = (String) OSCacheManager.getObjectFromCache("sessionId", null, 24*3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isNoneBlank(json)){
			JSONObject jsonObject = JSONObject.parseObject(json);
			request.setAttribute("openId", jsonObject.getString("openId"));
			return true;
		}
		ServiceResponse<Map<String,String>> resp = ServiceResponse.successResponse();
		resp = resp.setCode(BaseResponseCode.USER_NOT_LOGIN.getCode());
		resp = resp.setMsg(BaseResponseCode.USER_NOT_LOGIN.getMsg());
		HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
		SendMsgUtil.sendJsonMessage(response, result);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//System.out.println(">>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//System.out.println(">>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
	}

}
