package com.dogiant.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.dogiant.cms.domain.website.Section;

public interface SectionRepo extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Section> {
	
	@Transactional(readOnly = true)
	@Query("select o from Section o where o.id =:id and o.status>=0")
	Section getSectionValidDataById(@Param("id")Long id);
	
	Section getSectionByCode(@Param("code")String code);
	
	Section getSectionByName(@Param("name")String name);

}
