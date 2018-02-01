package com.dogiant.cms.web.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogiant.cms.config.ImageConfig;
import com.dogiant.cms.domain.website.Section;
import com.dogiant.cms.service.SectionService;

@Controller
public class SectionWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private SectionService sectionService;
	
	@RequestMapping(value = "/section_input", method = RequestMethod.GET)
    public String sectionInput(Map<String, Object> model) {
		logger.info("/section_input");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "article");
        return "section_input";
    }
	
	@RequestMapping(value = "/section_list", method = RequestMethod.GET)
    public String sectionList(Map<String, Object> model) {
		logger.info("/section_list");
		
		model.put("menu", "article");
        return "section_list";
    }
	
	@RequestMapping(value = "/section_modify", method = RequestMethod.GET)
    public String sectionModify(@RequestParam(value = "id", required = true) Long id, Map<String, Object> model) {
		logger.info("/section_modify");
		model.put("fileHost", ImageConfig.fileHost);
		Section section = sectionService.getSection(id);
		model.put("section", section);
		model.put("menu", "article");
        return "section_modify";
    }
	
}
