package com.dogiant.cms.web.controller.todos;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dogiant.cms.service.BookService;

@Controller
public class TodosWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/todos_book_list", method = RequestMethod.GET)
    public String articleCatInput(Map<String, Object> model) {
		logger.info("/todos_book_list");
		
		model.put("menu", "todos");
        return "/todos_book_list";
    }

}
