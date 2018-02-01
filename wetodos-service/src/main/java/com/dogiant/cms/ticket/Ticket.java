package com.dogiant.cms.ticket;

import java.io.Serializable;

public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 421151324517937378L;
	private String id;
	private long creationTime;
	private int countOfUses;
	private String userId;
	private long lastTimeUsed;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public int getCountOfUses() {
		return countOfUses;
	}

	public void setCountOfUses(int countOfUses) {
		this.countOfUses = countOfUses;
	}

	public long getLastTimeUsed() {
		return lastTimeUsed;
	}

	public void setLastTimeUsed(long lastTimeUsed) {
		this.lastTimeUsed = lastTimeUsed;
	}
}
