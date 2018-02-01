package com.dogiant.cms.ticket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RegistryCleaner {

	protected final Logger log = LoggerFactory.getLogger(getClass());

	private TicketRegistry ticketRegistry;

	private ExpirationPolicy expirationPolicy;

	public void clean() {
		final List<Ticket> ticketsToRemove = new ArrayList<Ticket>();
		final Collection<Ticket> ticketsInCache;

		log.info("Starting cleaning of expired tickets from ticket registry at ["
				+ new Date() + "]");

		ticketsInCache = this.ticketRegistry.getTickets();

		for (final Ticket ticket : ticketsInCache) {
			if (expirationPolicy.isExpired(ticket)) {
				ticketsToRemove.add(ticket);
			}
		}

		log.info(ticketsToRemove.size()
				+ " found to be removed.  Removing now.");

		for (final Ticket ticket : ticketsToRemove) {
			this.ticketRegistry.deleteTicket(ticket.getId());
		}

		log.info("Finished cleaning of expired tickets from ticket registry at ["
				+ new Date() + "]");
	}

	/**
	 * @param ticketRegistry
	 *            The ticketRegistry to set.
	 */
	public void setTicketRegistry(final TicketRegistry ticketRegistry) {
		this.ticketRegistry = ticketRegistry;
	}

	public void setExpirationPolicy(ExpirationPolicy expirationPolicy) {
		this.expirationPolicy = expirationPolicy;
	}
}
