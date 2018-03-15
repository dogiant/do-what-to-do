package com.dogiant.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ChapterDao;
import com.dogiant.cms.domain.todos.Chapter;
import com.dogiant.cms.service.ChapterService;

@Service("chapterService")
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterDao chapterDao;

	@Override
	public Chapter save(Chapter chapter) {
		return chapterDao.save(chapter);
	}

	@Override
	public List<Chapter> findChaptersByBookId(Long id) {
		return chapterDao.findChaptersByBookId(id);
	}

	@Override
	public Chapter findChapterById(Long id) {
		return chapterDao.findChapterById(id);
	}

	@Override
	public Integer getChpaterCountByBookIds(Long[] bookIds) {
		// TODO Auto-generated method stub
		return chapterDao.getChpaterCountByBookIds(bookIds);
	}

}
