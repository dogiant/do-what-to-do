package com.dogiant.cms.web.controller.todos;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dogiant.cms.config.ImageConfig;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.domain.todos.Chapter;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.service.BookService;
import com.dogiant.cms.service.ChapterService;
import com.dogiant.cms.service.PhaseSevice;

@Controller
public class TodosWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PhaseSevice phaseService;
	
	@RequestMapping(value = "/todos_book_list", method = RequestMethod.GET)
    public String todosBookList(Map<String, Object> model) {
		logger.info("/todos_book_list");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_book_list";
    }
	
	@RequestMapping(value = "/todos_book_input", method = RequestMethod.GET)
    public String todosBookAdd(Map<String, Object> model) {
		logger.info("/todos_book_input");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_book_input";
    }

	@RequestMapping(value = "/todos_book_modify", method = RequestMethod.GET)
    public String articleModify(@RequestParam(value = "id", required = true) Long id,Map<String, Object> model) {
		logger.info("/todos_book_modify");
		model.put("fileHost", ImageConfig.fileHost);
		
		Book book = bookService.getBook(id);
		model.put("book", book);
		
		model.put("menu", "todos");
        return "todos_book_modify";
    }
	
	@RequestMapping(value = "/todos_book_preview", method = RequestMethod.GET)
    public String articlePreview(@RequestParam(value = "id", required = true) Long id,Map<String, Object> model) {
		logger.info("/todos_book_preview");
		model.put("fileHost", ImageConfig.fileHost);

		Book book = bookService.getBook(id);
		model.put("book", book);
		
		List<Chapter> chapterList = chapterService.findChaptersByBookId(id);
		for(Chapter chapter : chapterList){
			List<Phase> phases = phaseService.findPhasesByChapterid(chapter.getId());
		}
		
		model.put("menu", "todos");
        return "todos_book_preview";
    }
}
