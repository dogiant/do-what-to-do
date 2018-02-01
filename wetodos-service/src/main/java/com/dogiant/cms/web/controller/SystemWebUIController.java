package com.dogiant.cms.web.controller;

import java.util.Map;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogiant.cms.domain.admin.AdminUser;
import com.dogiant.cms.service.AdminUserService;

@Controller
public class SystemWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private AdminUserService adminUserService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Map<String, Object> model) {
		logger.info("login");
        return "login";
    }
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
		logger.info("main");
        return "main";
    }
	
	@RequestMapping(value = "/admin_input", method = RequestMethod.GET)
    public String adminInput(Map<String, Object> model) {
		logger.info("/admin_input");
		model.put("menu", "admin");
        return "adminUser_input";
    }
	
	@RequestMapping(value = "/admin_list", method = RequestMethod.GET)
    public String adminList(Map<String, Object> model) {
		logger.info("/admin_list");
		model.put("menu", "admin");
		return "adminUser_list";
    }
	
	@RequestMapping(value = "/admin_modify", method = RequestMethod.GET)
	public String adminModify(@RequestParam(value = "userId", required = true) Integer userId,
			Map<String, Object> model) {
		logger.info("/admin_modify");
		model.put("menu", "admin");
		AdminUser adminUser = adminUserService.getAdminUserByUserId(userId);
		if (adminUser == null) {
			throw new RuntimeException("用户不存在");
		}
		model.put("adminUser", adminUser);
		return "adminUser_modify";
    }
}
