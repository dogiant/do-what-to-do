package com.dogiant.cms.service.impl;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.BookDao;
import com.dogiant.cms.dao.LearningPlanDao;
import com.dogiant.cms.domain.dto.DataTablesResult;
import com.dogiant.cms.domain.dto.QueryResult;
import com.dogiant.cms.domain.todos.Book;
import com.dogiant.cms.domain.todos.LearningPlan;
import com.dogiant.cms.service.LearningPlanService;

@Service("learningPlanService")
public class LearningPlanServiceImpl implements LearningPlanService {
	
	@Autowired
	private LearningPlanDao leaningPlanDao;
	
	@Autowired
	private BookDao bookDao;

	@Override
	public DataTablesResult<LearningPlan> getLearningPlanDataTablesResult(
			Integer start, Integer length, String orderName, String orderDir,
			String searchValue) {
		QueryResult<LearningPlan> queryResult = leaningPlanDao.getLearningPlanQueryResult(start, length, orderName, orderDir, searchValue);
		
		for(LearningPlan learningPlan : queryResult.getResult()){
			if(StringUtils.isNotBlank(learningPlan.getBookIds())){
				StringTokenizer tokener = new StringTokenizer(learningPlan.getBookIds(), ",");
		        Long[] result = new Long[tokener.countTokens()];
		        int i=0;
		        while( tokener.hasMoreElements() ){
		        	result[i++] = Long.valueOf(tokener.nextToken());
		        }
				List<Book> bookList = bookDao.getBookListByIds(result);
				if(CollectionUtils.isNotEmpty(bookList)){
					StringBuffer sb = new StringBuffer();
					for(Book book : bookList){
						sb.append(book.getBookShow());
					}
					learningPlan.setBookShow(sb.toString());
				}else{
					learningPlan.setBookShow("");
				}
			}else{
				learningPlan.setBookShow("");
			}
		}
		
		DataTablesResult<LearningPlan> dataTableResult = new DataTablesResult<LearningPlan>();
		dataTableResult.setData(queryResult.getResult());
		dataTableResult.setRecordsTotal(queryResult.getRecordnum());
		dataTableResult.setRecordsFiltered(queryResult.getRecordnum());
		return dataTableResult;
	}

	@Override
	public LearningPlan save(LearningPlan learningPlan) {
		return leaningPlanDao.save(learningPlan);
		
	}

	@Override
	public LearningPlan findLearningPlanById(Long id) {
		return leaningPlanDao.findLearningPlanById(id);
	}

}
