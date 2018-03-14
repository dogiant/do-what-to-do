package com.dogiant.cms.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dogiant.cms.dao.ChapterDao;
import com.dogiant.cms.domain.todos.Chapter;
import com.dogiant.cms.repo.ChapterRepo;

@Service("chapterDao")
public class ChapterDaoImpl implements ChapterDao {
	
	@Resource
	private ChapterRepo chapterRepo;

	@Override
	public Chapter save(Chapter chapter) {
		return chapterRepo.save(chapter);
	}

	@Override
	public List<Chapter> findChaptersByBookId(Long id) {
		return chapterRepo.findChaptersByBookId(id);
	}

	@Override
	public Chapter findChapterById(Long id) {
		return chapterRepo.findOne(id);
	}

}
