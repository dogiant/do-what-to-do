package com.dogiant.cms.web.controller.todos;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.HttpResult;
import com.dogiant.cms.domain.dto.ServiceResponse;
import com.dogiant.cms.domain.dto.ServiceResponse2HttpResult;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.domain.todos.Chapter;
import com.dogiant.cms.domain.todos.LearningPlan;
import com.dogiant.cms.domain.todos.Phase;
import com.dogiant.cms.exception.ServiceExInfo;
import com.dogiant.cms.service.BookService;
import com.dogiant.cms.service.ChapterService;
import com.dogiant.cms.service.LearningPlanService;
import com.dogiant.cms.service.PhaseService;
import com.dogiant.cms.utils.QueryStringParser;

@RestController
public class TodosRestAPIController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private BookService bookService;
	
	@Autowired
	private ChapterService chapterService;
	
	@Autowired
	private PhaseService phaseService;
	
	@Autowired
	private LearningPlanService learningPlanService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@ResponseBody
	@RequestMapping(value = "/api/todos/book/add", method = RequestMethod.POST)
	public HttpResult<?> bookAdd(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Book book) {

		Date now = new Date();
		book.setCtime(now);
		book.setMtime(now);
		book.setOperator((String) request.getAttribute("userName"));
		book.setType(1);
		// 0 先发后审 -1先审后发
		book.setStatus(0);

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			bookService.addBook(book);
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
	@RequestMapping(value = "/api/todos/book/delete", method = RequestMethod.POST)
	public HttpResult<?> bookDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				Book book = bookService.getBook(id);
				if (book != null) {
					book.setStatus(-3);
					bookService.updateBook(book);
				}
			} catch (Exception e) {
				e.printStackTrace();

				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}

	@ResponseBody
	@RequestMapping(value = "/api/todos/book/update", method = RequestMethod.POST)
	public HttpResult<?> bookUpdate(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Book book) {

		ServiceResponse<?> resp = ServiceResponse.successResponse();

		Book bookFromDB = bookService.getBook(book.getId());
		if (bookFromDB == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		Date now = new Date();
		book.setCtime(bookFromDB.getCtime());
		book.setMtime(now);
		book.setOperator((String) request.getAttribute("userName"));
		book.setType(1);
		// 0 先发后审 -1先审后发
		book.setStatus(0);

		try {
			bookService.updateBook(book);
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
	@RequestMapping(value = "/api/todos/book/enable", method = RequestMethod.POST)
	public HttpResult<?> articleReEnable(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				Book book = bookService.getBook(id);
				if (book != null) {
					book.setStatus(1);
					bookService.updateBook(book);
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}

	@ResponseBody
	@RequestMapping(value = "/api/todos/book/preview", method = {RequestMethod.GET,RequestMethod.POST})
	public HttpResult<?> articlePreview(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) {

		ServiceResponse<Book> resp = ServiceResponse.successResponse();

		try {
			Book book = bookService.getBook(id);
			if (book == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			resp.setData(book);
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
	@RequestMapping(value = "/api/todos/book/{id}", method = RequestMethod.GET)
	public HttpResult<?> bookView(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") Long id) {

		ServiceResponse<Book> resp = ServiceResponse.successResponse();

		try {
			Book book = bookService.getBook(id);
			if (book == null) {
				resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			if (book.getStatus() < 0) {
				resp = resp.setCode(ServiceExInfo.NO_RESULT_ERROR_EXCEPTION.getCode());
				resp = resp.setMsg(ServiceExInfo.NO_RESULT_ERROR_EXCEPTION.getMessage());
				HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
				return result;
			}
			resp.setData(book);
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
	@RequestMapping(value = "/api/todos/book/list", method = { RequestMethod.POST, RequestMethod.GET })
	public DataTablesResult<Book> bookList(HttpServletRequest request, HttpServletResponse response) {

		String tranToken = request.getQueryString();
		Map<String, String> params = QueryStringParser.queryStringParser(tranToken, "utf-8");

		Integer draw = null;
		Integer start = null;
		Integer length = null;

		try {
			draw = Integer.parseInt(request.getParameter("draw"));
			start = Integer.parseInt(request.getParameter("start"));
			length = Integer.parseInt(request.getParameter("length"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String orderColumn = null;
		String orderName = null;
		String orderDir = null;
		String searchValue = null;

		orderColumn = params.get("order[0][column]");
		orderDir = params.get("order[0][dir]");
		searchValue = params.get("search[value]");
		if (StringUtils.isNotEmpty(orderColumn)) {
			orderName = params.get("columns[" + orderColumn + "][data]");
		}

		if (draw == null || start == null || length == null) {
			return null;
		}

		try {
			DataTablesResult<Book> dataTablesResult = bookService.getBookDataTablesResult(start,
					length, orderName, orderDir, searchValue);
			dataTablesResult.setDraw(draw);
			return dataTablesResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/api/todos/chapter/save", method = RequestMethod.POST)
	public HttpResult<?> chapterSave(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Chapter chapter) {

		Date now = new Date();
		chapter.setCtime(now);
		chapter.setMtime(now);
		// 0 先发后审 -1先审后发
		chapter.setStatus(0);

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			chapterService.save(chapter);
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
	@RequestMapping(value = "/api/todos/phase/save", method = RequestMethod.POST)
	public HttpResult<?> chapterSave(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute Phase phase) {

		Date now = new Date();
		phase.setEditor((String)request.getAttribute("userName"));
		phase.setCtime(now);
		phase.setMtime(now);
		// 0 先发后审 -1先审后发
		phase.setStatus(0);

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			phaseService.save(phase);
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
	@RequestMapping(value = "/api/todos/chapter/delete", method = RequestMethod.POST)
	public HttpResult<?> chapterDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				Chapter chapter = chapterService.findChapterById(id);
				if (chapter != null) {
					chapter.setStatus(-3);
					chapterService.save(chapter);
				}
			} catch (Exception e) {
				e.printStackTrace();

				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	@ResponseBody
	@RequestMapping(value = "/api/todos/phase/delete", method = RequestMethod.POST)
	public HttpResult<?> phaseDelete(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) {

		ServiceResponse<List<Long>> resp = ServiceResponse.successResponse();
		if (ids == null) {
			resp = resp.setCode(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getCode());
			resp = resp.setMsg(ServiceExInfo.PARAMETER_ERROR_EXCEPTION.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}

		List<Long> errorIds = new ArrayList<Long>();
		for (Long id : ids) {
			try {
				Phase phase = phaseService.findPhaseById(id);
				if (phase != null) {
					phase.setStatus(-3);
					phaseService.save(phase);
				}
			} catch (Exception e) {
				e.printStackTrace();

				errorIds.add(id);
			}
		}

		if (CollectionUtils.isNotEmpty(errorIds)) {
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			resp.setData(errorIds);
		}

		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "api/todos/plan/list", method = { RequestMethod.POST, RequestMethod.GET })
	public DataTablesResult<LearningPlan> planList(HttpServletRequest request, HttpServletResponse response) {

		String tranToken = request.getQueryString();
		Map<String, String> params = QueryStringParser.queryStringParser(tranToken, "utf-8");

		Integer draw = null;
		Integer start = null;
		Integer length = null;

		try {
			draw = Integer.parseInt(request.getParameter("draw"));
			start = Integer.parseInt(request.getParameter("start"));
			length = Integer.parseInt(request.getParameter("length"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		String orderColumn = null;
		String orderName = null;
		String orderDir = null;
		String searchValue = null;

		orderColumn = params.get("order[0][column]");
		orderDir = params.get("order[0][dir]");
		searchValue = params.get("search[value]");
		if (StringUtils.isNotEmpty(orderColumn)) {
			orderName = params.get("columns[" + orderColumn + "][data]");
		}

		if (draw == null || start == null || length == null) {
			return null;
		}

		try {
			DataTablesResult<LearningPlan> dataTablesResult = learningPlanService.getLearningPlanDataTablesResult(start,
					length, orderName, orderDir, searchValue);
			dataTablesResult.setDraw(draw);
			return dataTablesResult;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
	@ResponseBody
	@RequestMapping(value = "/api/todos/plan/add", method = RequestMethod.POST)
	public HttpResult<?> planSave(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute LearningPlan learningPlan) {

		Date now = new Date();
		Integer days = 0;
		if (StringUtils.isNotBlank(learningPlan.getBookIds())) {
			StringTokenizer tokener = new StringTokenizer(learningPlan.getBookIds(), ",");
	        Long[] result = new Long[tokener.countTokens()];
	        int i=0;
	        while( tokener.hasMoreElements() ){
	        	result[i++] = Long.valueOf(tokener.nextToken());
	        }
			days = chapterService.getChpaterCountByBookIds(result);
			learningPlan.setDays(days);
		}
		
		if (learningPlan.getType() != null
				&& learningPlan.getType().intValue() == 1) {
			ZoneId zoneId = ZoneId.systemDefault();
			
			//如果是固定学期计划
			Date startDate = learningPlan.getStartDate();
			Instant instant = startDate.toInstant();
		    // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
		    LocalDate startLocalDate = instant.atZone(zoneId).toLocalDate();
		    
		    LocalDate endLocalDate = startLocalDate.plusDays(days-1);
	        ZonedDateTime zdt = endLocalDate.atStartOfDay(zoneId);
	        Date endDate = Date.from(zdt.toInstant());
	        
	        learningPlan.setEndDate(endDate);
		}

		learningPlan.setCreator((String)request.getAttribute("userName"));
		learningPlan.setCtime(now);
		learningPlan.setMtime(now);
		// 0 先发后审 -1先审后发
		learningPlan.setStatus(0);

		ServiceResponse<?> resp = ServiceResponse.successResponse();
		try {
			learningPlanService.save(learningPlan);
		} catch (Exception e) {
			e.printStackTrace();
			resp = resp.setCode(ServiceExInfo.SYSTEM_ERROR.getCode());
			resp = resp.setMsg(ServiceExInfo.SYSTEM_ERROR.getMessage());
			HttpResult<?> result = ServiceResponse2HttpResult.transfer(resp);
			return result;
		}
		return ServiceResponse2HttpResult.transfer(resp);
	}
	
	public static void main(String[] args) {
		
		
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();

        // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
        LocalDate today = instant.atZone(zoneId).toLocalDate();
        System.out.println("Date = " + date);
        System.out.println("LocalDate = " + today);
        
        
		//Get the Year, check if it's leap year
		System.out.println("Year "+today.getYear()+" is Leap Year? "+today.isLeapYear());
		//Compare two LocalDate for before and after
		System.out.println("Today is before 01/01/2015? "+today.isBefore(LocalDate.of(2015,1,1)));
		//Create LocalDateTime from LocalDate
		System.out.println("Current Time="+today.atTime(LocalTime.now()));
		//plus and minus operations
		System.out.println("10 days after today will be "+today.plusDays(10));
	}
}
