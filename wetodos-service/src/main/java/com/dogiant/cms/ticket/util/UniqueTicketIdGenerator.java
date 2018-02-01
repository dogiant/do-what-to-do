package com.dogiant.cms.ticket.util;

public interface UniqueTicketIdGenerator {

    /**
     * Return a new unique ticket id beginning with the prefix.
     * 
     * @param prefix The prefix we want attached to the ticket.
     * @return the unique ticket id
     */
    String getNewTicketId(String prefix);
}
