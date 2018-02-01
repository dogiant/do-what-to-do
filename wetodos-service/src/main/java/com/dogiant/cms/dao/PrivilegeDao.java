package com.dogiant.cms.dao;

import com.dogiant.cms.domain.admin.Privilege;
import com.dogiant.cms.domain.admin.PrivilegePK;
import com.dogiant.cms.domain.dto.QueryResult;

public interface PrivilegeDao {
	
	public QueryResult<Privilege> getScrollDataNative(int firstIndex, int maxResult);
	
	public void delete(PrivilegePK[] privilegePKs);
	
	public void delete(PrivilegePK privilegePK);
}
