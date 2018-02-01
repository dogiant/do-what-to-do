package com.dogiant.cms.domain.dto;

import java.io.Serializable;
import java.util.List;

public class PagedResult<T> implements Serializable {

	private static final long serialVersionUID = 8004323068239831454L;

	int pageNo; // 当前页数

	int pageRows; // 每页最多返回多少条记录

	int pageRowsReal; // 当前页返回记录数

	int allPage; // 共多少页

	int allRows; // 共有多少行记录

	List<T> rs; // 结果

	public boolean isNextPage() {
		return (allPage == 1 || pageNo == allPage);
	}

	public boolean isPrevPage() {
		return (allPage == 1 || pageNo == 1);
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getPageRowsReal() {
		return pageRowsReal;
	}

	public void setPageRowsReal(int pageRowsReal) {
		this.pageRowsReal = pageRowsReal;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public int getAllRows() {
		return allRows;
	}

	public void setAllRows(int allRows) {
		this.allRows = allRows;
	}

	public List<T> getRs() {
		return rs;
	}

	public void setRs(List<T> rs) {
		this.rs = rs;
	}

	public PagedResult<T> getPagedInfo(int pageNo, int pageRows, int counts,
			List<T> rs) {
		PagedResult<T> pr = new PagedResult<T>();
		int allP = counts / pageRows + (counts % pageRows == 0 ? 0 : 1);
		if (pageNo > allP) {
			pageNo = allP;
		}
		if (pageNo == 0) {
			pageNo = 1;
		}
		pr.setAllPage(allP);
		pr.setAllRows(counts);
		pr.setPageNo(pageNo);
		pr.setPageRows(pageRows);
		pr.setPageRowsReal(rs == null ? 0 : rs.size());
		pr.setRs(rs);
		return pr;
	}

}
