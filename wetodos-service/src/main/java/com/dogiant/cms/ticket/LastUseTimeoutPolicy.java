package com.dogiant.cms.ticket;

public class LastUseTimeoutPolicy implements ExpirationPolicy {

	private final long timeToKillInMilliSeconds;

	public LastUseTimeoutPolicy(final long timeToKillInMilliSeconds) {
		this.timeToKillInMilliSeconds = timeToKillInMilliSeconds;
	}

	@Override
	public boolean isExpired(final Ticket ticket) {
		return (ticket == null)
				|| (System.currentTimeMillis() - ticket.getLastTimeUsed() >= this.timeToKillInMilliSeconds);
	}
}
