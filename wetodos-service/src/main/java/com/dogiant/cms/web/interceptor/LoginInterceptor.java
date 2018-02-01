package com.dogiant.cms.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dogiant.cms.cookie.AdminUserInfo;
import com.dogiant.cms.cookie.CookieUtil;

public class LoginInterceptor implements HandlerInterceptor {

	protected final static Logger logger = Logger.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map<String, Object> userInfoMap = CookieUtil.getUserFromCookie(request);
		StringBuffer url = request.getRequestURL();
		String contextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length())
				.append(request.getServletContext().getContextPath()).append("/").toString();
		if (userInfoMap != null) {
			AdminUserInfo userInfo = (AdminUserInfo) userInfoMap.get("user");
			request.setAttribute("userId", userInfo.getUserId());
			request.setAttribute("userName", userInfo.getUserName());
			request.setAttribute("nickname", userInfo.getNickname());
			request.setAttribute("lastLoginTime", userInfo.getLastLoginTime());
			request.setAttribute("contextUrl", contextUrl);
			return true;
		} else {
			response.sendRedirect(contextUrl + "login");
			return false;
		}
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
