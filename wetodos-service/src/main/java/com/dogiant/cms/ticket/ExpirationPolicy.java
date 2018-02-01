package com.dogiant.cms.ticket;

public interface ExpirationPolicy {
    boolean isExpired(Ticket ticket);
}
