package com.dogiant.cms.ticket;

public class CreationTimeoutPolicy implements ExpirationPolicy {

	private final long timeToKillInMilliSeconds;

	public CreationTimeoutPolicy(final long timeToKillInMilliSeconds) {
		this.timeToKillInMilliSeconds = timeToKillInMilliSeconds;
	}

	@Override
	public boolean isExpired(final Ticket ticket) {
		return (ticket == null)
				|| (System.currentTimeMillis() - ticket.getCreationTime() >= this.timeToKillInMilliSeconds);
	}

}
