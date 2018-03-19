package com.dogiant.cms.web.controller.todos;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
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
import com.dogiant.cms.domain.todos.LearningPlan;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.service.BookService;
import com.dogiant.cms.service.ChapterService;
import com.dogiant.cms.service.LearningPlanService;
import com.dogiant.cms.service.PhaseService;

@Controller
public class TodosWebUIController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PhaseService phaseService;
	
	@Autowired
	private LearningPlanService learningPlanService;
	
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
			List<Phase> phases = phaseService.findPhasesByChapterId(chapter.getId());
			chapter.setPhases(phases);
		}
		model.put("chapterList", chapterList);
		
		model.put("menu", "todos");
        return "todos_book_preview";
    }
	
	
	@RequestMapping(value = "/todos_plan_list", method = RequestMethod.GET)
    public String todosPlanList(Map<String, Object> model) {
		logger.info("/todos_plan_list");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_plan_list";
    }
	
	@RequestMapping(value = "/todos_plan_input", method = RequestMethod.GET)
    public String todosPlanInput(Map<String, Object> model) {
		logger.info("/todos_plan_input");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_plan_input";
    }
	
	@RequestMapping(value = "/todos_plan_modify", method = RequestMethod.GET)
    public String todosPlanModify(@RequestParam(value = "id", required = true) Long id,Map<String, Object> model) {
		logger.info("/todos_plan_modify");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
		
		LearningPlan learningPlan = learningPlanService.findLearningPlanById(id);
		if(learningPlan!=null && StringUtils.isNotBlank(learningPlan.getBookIds())){
			StringTokenizer tokener = new StringTokenizer(learningPlan.getBookIds(), ",");
	        Long[] result = new Long[tokener.countTokens()];
	        int i=0;
	        while( tokener.hasMoreElements() ){
	        	result[i++] = Long.valueOf(tokener.nextToken());
	        }
			List<Book> bookList = bookService.getBookListByIds(result);
			learningPlan.setBooks(bookList);
		}
		model.put("learningPlan", learningPlan);
		
        return "/todos_plan_modify";
    }
	
	
	@RequestMapping(value = "/todos_banner_list", method = RequestMethod.GET)
    public String todosBannerList(Map<String, Object> model) {
		logger.info("/todos_banner_list");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_banner_list";
    }
	
	@RequestMapping(value = "/todos_banner_input", method = RequestMethod.GET)
    public String todosBannerInput(Map<String, Object> model) {
		logger.info("/todos_plan_input");
		model.put("fileHost", ImageConfig.fileHost);
		model.put("menu", "todos");
        return "/todos_banner_input";
    }
}
