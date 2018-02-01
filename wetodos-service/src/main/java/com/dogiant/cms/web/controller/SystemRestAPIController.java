package com.dogiant.cms.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dogiant.cms.cookie.AdminUserInfo;
import com.dogiant.cms.cookie.CookieUtil;
import com.dogiant.cms.domain.admin.AdminUser;
import com.dogiant.cms.domain.dto.BusinessErrorCode;
import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.domain.dto.PagedResult;
import com.dogiant.cms.domain.dto.ServiceResponse;
import com.dogiant.cms.domain.dto.ServiceResponse2HttpResult;
import com.dogiant.cms.exception.CommException;
import com.dogiant.cms.exception.ServiceExInfo;
import com.dogiant.cms.service.AdminUserService;
import com.dogiant.cms.ticket.Ticket;
import com.dogiant.cms.ticket.TicketAuthHandler;
import com.dogiant.cms.ticket.util.UniqueTicketIdGenerator;
import com.dogiant.cms.utils.IpAddressUtil;

@RestController
public class SystemRestAPIController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private TicketAuthHandler ticketAuthHandler;
	@Autowired
	private UniqueTicketIdGenerator keyGenerator;

	@ResponseBody
	@RequestMapping(value = "/api/login", method = RequestMethod.POST)
	public HttpResult<?> login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "callback", required = false) String callback,
			@RequestParam(value = "returnUrl", required = false) String returnUrl) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			if (StringUtils.isNotEmpty(callback)) {
				try {
					userName = URLDecoder.decode(userName, "UTF-8");
					password = URLDecoder.decode(password, "UTF-8");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
				resp = resp.setCode(BusinessErrorCode.PARAM_NULL.code);
				resp = resp.setMsg(String.format(BusinessErrorCode.PARAM_NULL.msg, "username or password"));
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}

			AdminUser user = adminUserService.findUserByUserNameValid(userName);
			if (user == null) {
				resp = resp.setCode(ServiceExInfo.NO_AUTH_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.NO_AUTH_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}

			if (StringUtils.isNotBlank(password)) {
				String md5Password = user.getPassword();
				if (StringUtils.isNotEmpty(callback)) {
					if (!md5Password.equalsIgnoreCase(password)) {
						resp = resp.setCode(ServiceExInfo.USER_PASS_ERROR.getCode());
						resp = resp.setMsg(ServiceExInfo.USER_PASS_ERROR.getMessage());
						HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
						return result;
					}
				} else {
					if (!md5Password.equalsIgnoreCase(DigestUtils.md5DigestAsHex(password.getBytes()))) {
						resp = resp.setCode(ServiceExInfo.USER_PASS_ERROR.getCode());
						resp = resp.setMsg(ServiceExInfo.USER_PASS_ERROR.getMessage());
						HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
						return result;
					}
				}
				String authKey = this.generateTicket(user);

				CookieUtil.setUserCookie(response, user, authKey);

				String lastLoginIp = IpAddressUtil.getIpAddr(request);
				user.setLastLoginIp(lastLoginIp);
				user.setLastLoginTime(new Date());
				adminUserService.saveOrUpdate(user);
				logger.info(
						user.getUserName() + " " + new Date().toString() + " from " + lastLoginIp + " login success.");
				resp.setPrompt(returnUrl);
				return ServiceResponse2HttpResult.transfer(resp);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}

	/**
	 * 生成Ticket并登记票据
	 * 
	 * @param user
	 * @return authKey
	 */
	private String generateTicket(AdminUser user) {
		String key = this.keyGenerator.getNewTicketId(String.valueOf(user.getUserId()));
		Ticket ticket = new Ticket();
		long now = System.currentTimeMillis();
		ticket.setCreationTime(now);
		ticket.setLastTimeUsed(now);
		ticket.setId(key);
		ticket.setUserId(user.getUserId().toString());
		ticketAuthHandler.getTicketRegistry().addTicket(ticket);
		return key;
	}

	@ResponseBody
	@RequestMapping(value = "/api/logout", method = RequestMethod.GET)
	public HttpResult<?> logout(HttpServletRequest request, HttpServletResponse response) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			Map<String, Object> userMap = CookieUtil.getUserFromCookie(request);
			if (userMap == null) {
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			String authKey = (String) userMap.get("authkey");
			boolean b1 = ticketAuthHandler.deleteAuthKey(authKey);
			boolean b2 = CookieUtil.deleteAuthCookie(response);
			if (b1 && b2) {
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			} else {
				resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
				resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}

	@RequestMapping(value = "/api/checkAdminUserNameExists", method = RequestMethod.POST)
	public String checkAdminUserNameExists(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userName", required = true) String userName) {

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
		AdminUser adminUser = null;
		if (StringUtils.isNotBlank(userName)) {
			adminUser = adminUserService.findUserByUserNameValid(userName);
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || adminUser != null) {
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

	@RequestMapping(value = "/api/checkAdminNicknameExists", method = RequestMethod.POST)
	public String checkAdminNicknameExists(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "nickname", required = true) String nickname) {

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
		AdminUser adminUser = null;
		if (StringUtils.isNotBlank(nickname)) {
			adminUser = adminUserService.findUserByNicknameValid(nickname);
		} else {
			returnval = false;
		}
		try {
			// 直接进行文本操作
			if (!returnval || adminUser != null) {
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
	@RequestMapping(value = "/api/admin/add", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> adminUserAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute AdminUser adminUser) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		if (adminUser != null) {
			Date now = new Date();
			adminUser.setCtime(now);
			adminUser.setMtime(now);
			adminUser.setIsValid(1);
		} else {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		try {
			adminUser.setPassword(DigestUtils.md5DigestAsHex(adminUser.getPassword().getBytes()));
			adminUserService.saveOrUpdate(adminUser);
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
	@RequestMapping(value = "/api/admin/update", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> adminUserUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute AdminUser adminUser) throws CommException {
		
		ServiceResponse<?> resp = ServiceResponse.successResponse();
		AdminUser adminFromDB = null;
		if (adminUser != null && adminUser.getUserId() != null) {
			//权限校验
			if(CookieUtil.getUserFromCookie(request)!=null){
				AdminUserInfo userInfo = (AdminUserInfo) CookieUtil.getUserFromCookie(request).get("user");
				if(!"admin".equals(userInfo.getUserName()) && !userInfo.getUserId().equals(adminUser.getUserId())){
					throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
				}
			}else{
				throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
			}
			
			adminFromDB = adminUserService.getAdminUserByUserId(adminUser.getUserId());
			if (adminFromDB == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		} else {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		try {
			if(StringUtils.isNotBlank(adminUser.getPassword()) && !adminUser.getPassword().equals(adminFromDB.getPassword())){
				adminUser.setPassword(DigestUtils.md5DigestAsHex(adminUser.getPassword().getBytes()));
			}else{
				adminUser.setPassword(adminFromDB.getPassword());
			}
			Date now = new Date();
			adminUser.setMtime(now);
			adminUser.setCtime(adminFromDB.getCtime());
			adminUser.setIsValid(adminFromDB.getIsValid());
			adminUser.setLastLoginIp(adminFromDB.getLastLoginIp());
			adminUser.setLastLoginTime(adminFromDB.getLastLoginTime());
			adminUser.setRoles(adminFromDB.getRoles());
			adminUser.setPrivileges(adminFromDB.getPrivileges());
			adminUserService.saveOrUpdate(adminUser);
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
	@RequestMapping(value = "/api/admin/list", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> adminUserList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "pageNo", required = false, defaultValue="1") int pageNo,
			@RequestParam(value = "pageRows", required = false, defaultValue="15") int pageRows) {
		
		ServiceResponse<PagedResult<AdminUser>> resp = ServiceResponse.successResponse();
		try {
			userName = URLDecoder.decode(userName, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			PagedResult<AdminUser> pagedResult = adminUserService.getAdminUserQueryResult(pageNo, pageRows, beginTime,
					endTime, userName);
			resp.setData(pagedResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/admin/enable", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> adminUserEnable(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute AdminUser adminUser) throws CommException {
		
		ServiceResponse<?> resp = ServiceResponse.successResponse();
		AdminUser adminFromDB = null;
		if (adminUser != null && adminUser.getUserId() != null) {
			//权限校验
			if(CookieUtil.getUserFromCookie(request)!=null){
				AdminUserInfo userInfo = (AdminUserInfo) CookieUtil.getUserFromCookie(request).get("user");
				if(!"admin".equals(userInfo.getUserName()) && !userInfo.getUserId().equals(adminUser.getUserId())){
					throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
				}
			}else{
				throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
			}
			
			adminFromDB = adminUserService.getAdminUserByUserId(adminUser.getUserId());
			if (adminFromDB == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		} else {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		try {
			adminFromDB.setIsValid(1);
			adminFromDB.setMtime(new Date());
			adminUserService.saveOrUpdate(adminFromDB);
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
	@RequestMapping(value = "/api/admin/disable", method = { RequestMethod.POST, RequestMethod.GET })
	public HttpResult<?> adminUserDisable(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute AdminUser adminUser) throws CommException {
		
		ServiceResponse<?> resp = ServiceResponse.successResponse();
		AdminUser adminFromDB = null;
		if (adminUser != null && adminUser.getUserId() != null) {
			//权限校验
			if(CookieUtil.getUserFromCookie(request)!=null){
				AdminUserInfo userInfo = (AdminUserInfo) CookieUtil.getUserFromCookie(request).get("user");
				if(!"admin".equals(userInfo.getUserName()) && !userInfo.getUserId().equals(adminUser.getUserId())){
					throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
				}
			}else{
				throw new CommException(ServiceExInfo.NO_AUTH_EXCEPTION);
			}
			
			adminFromDB = adminUserService.getAdminUserByUserId(adminUser.getUserId());
			if (adminFromDB == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
		} else {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		try {
			adminFromDB.setIsValid(0);
			adminFromDB.setMtime(new Date());
			adminUserService.saveOrUpdate(adminFromDB);
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
