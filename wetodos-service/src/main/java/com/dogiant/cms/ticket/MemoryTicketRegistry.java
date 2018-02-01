package com.dogiant.cms.ticket;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("ticketRegistry")
public class MemoryTicketRegistry implements TicketRegistry {
	protected final Logger log = LoggerFactory.getLogger(getClass());

	/** A HashMap to contain the tickets. */
	private final Map<String, Ticket> cache;
	// 存储用户 在线信息
	private final Map<String, String> userOnlineMap = new ConcurrentHashMap<String, String>();

	public MemoryTicketRegistry() {
		this.cache = new ConcurrentHashMap<String, Ticket>();
	}

	public MemoryTicketRegistry(int initialCapacity, final float loadFactor,
			final int concurrencyLevel) {
		this.cache = new ConcurrentHashMap<String, Ticket>(initialCapacity,
				loadFactor, concurrencyLevel);
	}

	public void addTicket(final Ticket ticket) {

		if (log.isDebugEnabled()) {
			log.debug("Added ticket [" + ticket.getId() + "] to registry.");
		}
		this.cache.put(ticket.getId(), ticket);

		addUserOnline(ticket.getUserId());
		log.debug("add user online: " + ticket.getUserId());
		log.debug("userOnlineMap: " + this.userOnlineMap);
		log.debug("this: " + this);

	}

	public void addUserOnline(String userId) {
		this.userOnlineMap.put(userId, "true");
	}

	public void removeUserOnline(String userId) {
		this.userOnlineMap.remove(userId);
	}

	public boolean isUserOnline(String userId) {
		log.debug("isUserOnline: " + this.userOnlineMap + " " + userId);
		log.debug("isUserOnline: " + this.userOnlineMap.containsKey(userId));
		log.debug("this: " + this);
		return this.userOnlineMap.containsKey(userId);
	}

	public Ticket getTicket(final String ticketId) {
		if (ticketId == null) {
			return null;
		}

		if (log.isDebugEnabled()) {
			log.debug("Attempting to retrieve ticket [" + ticketId + "]");
		}
		final Ticket ticket = this.cache.get(ticketId);

		if (ticket != null) {
			log.debug("Ticket [" + ticketId + "] found in registry.");
		}

		return ticket;
	}

	public boolean deleteTicket(final String ticketId) {
		if (ticketId == null) {
			return false;
		}
		if (log.isDebugEnabled()) {
			log.debug("Removing ticket [" + ticketId + "] from registry");
		}
		Ticket t = getTicket(ticketId);
		if (t != null) {
			log.debug("Ticket [" + ticketId + "] found in registry.");
			removeUserOnline(t.getUserId());
		}
		this.cache.remove(ticketId) ;
		t = getTicket(ticketId);
		if(t==null){
			return true;
		}
		return false;
	}

	public Collection<Ticket> getTickets() {
		return Collections.unmodifiableCollection(this.cache.values());
	}

	public Ticket getTicket(String ticketId, Class<? extends Ticket> clazz) {
		return getTicket(ticketId);
	}

}
