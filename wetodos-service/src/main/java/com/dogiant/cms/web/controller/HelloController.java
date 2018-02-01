package com.dogiant.cms.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello spring boot.";
	}

	@RequestMapping(value = "/ex", method = RequestMethod.GET)
	public String exception() throws Exception{
		throw new Exception("发生错误");
	}

}
