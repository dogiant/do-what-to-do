package com.dogiant.cms.service;

import java.util.List;

import com.dogiant.cms.domain.todos.Chapter;

public interface ChapterService {

	Chapter save(Chapter chapter);

	List<Chapter> findChaptersByBookId(Long id);

	Chapter findChapterById(Long id);

}
