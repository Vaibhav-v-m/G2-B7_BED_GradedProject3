package com.greatlearning.ticket.service;

import java.util.List;
import com.greatlearning.ticket.entity.Ticket;

public interface TicketService {

	List<Ticket> listTickets();

	Ticket saveTicket(Ticket ticket);

	// Update - Start
	Ticket updateTicket(Ticket ticket);

	Ticket getTicketById(Long id);
	// Update - End

//	delete
	void deleteById(Long id);

//	Search 
	List<Ticket> getByKeyword(String keyword);

}