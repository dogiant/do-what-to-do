package com.dogiant.cms.domain.dto;

import java.util.List;

public class QueryResult<T> {
	private List<T> result;
	private long recordnum;

	/**
	 * @return the result
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * @return the recordnum
	 */
	public long getRecordnum() {
		return recordnum;
	}

	/**
	 * @param recordnum
	 *            the recordnum to set
	 */
	public void setRecordnum(long recordnum) {
		this.recordnum = recordnum;
	}
}
