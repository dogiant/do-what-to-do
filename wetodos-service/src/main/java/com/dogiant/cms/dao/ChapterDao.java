package com.dogiant.cms.dao;

import java.util.List;

import com.dogiant.cms.domain.todos.Chapter;

public interface ChapterDao {

	Chapter save(Chapter chapter);

	List<Chapter> findChaptersByBookId(Long id);

}
