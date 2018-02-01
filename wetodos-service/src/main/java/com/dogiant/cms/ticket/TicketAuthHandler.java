package com.dogiant.cms.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketAuthHandler {
	
	@Autowired
	private TicketRegistry ticketRegistry;

	public String checkAuthKey(String key) {
		Ticket ticket = ticketRegistry.getTicket(key);
		if (ticket == null) {
			return "ERROR_KEY";
		} else {
			ticket.setCountOfUses(ticket.getCountOfUses() + 1);
			ticket.setLastTimeUsed(System.currentTimeMillis());
			addUserOnline(ticket.getUserId());
			return "SUCCESS";
		}
	}

	public String getUserIdByAuthKey(String authKey) {
		Ticket ticket = ticketRegistry.getTicket(authKey);
		if (ticket == null) {
			return null;
		} else {
			ticket.setCountOfUses(ticket.getCountOfUses() + 1);
			ticket.setLastTimeUsed(System.currentTimeMillis());
			addUserOnline(ticket.getUserId());
			return ticket.getUserId();
		}
	}
	
	public boolean deleteAuthKey(String authKey){
		return ticketRegistry.deleteTicket(authKey);
	}

	public void addUserOnline(String userId) {
		((MemoryTicketRegistry) (ticketRegistry)).addUserOnline(userId);
	}

	public void removeUserOnline(String userId) {
		((MemoryTicketRegistry) (ticketRegistry)).removeUserOnline(userId);
	}

	public Boolean isUserOnline(String userId) {
		return ((MemoryTicketRegistry) (ticketRegistry)).isUserOnline(userId);
	}

	public void setTicketRegistry(TicketRegistry ticketRegistry) {
		this.ticketRegistry = ticketRegistry;
	}

	public TicketRegistry getTicketRegistry() {
		return ticketRegistry;
	}
}
