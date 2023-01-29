package com.greatlearning.ticket.conroller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.ticket.entity.Ticket;
import com.greatlearning.ticket.service.TicketService;

@Controller
public class TicketController {

	private TicketService ticketService;

	public TicketController(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@GetMapping("/tickets")
	public String listTickets(Model model) {

		List<Ticket> tickets = ticketService.listTickets();

		model.addAttribute("tickets", tickets);
		return "tickets";

	}

//	for adding tickets

	@GetMapping("/tickets/new")
	public String addticketButtonClicked(Model model) {

		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "create_ticket";

	}

	@PostMapping("/tickets")
	public String submitButtonClickedForAddTicket(@ModelAttribute("ticket") Ticket ticket) {

		ticketService.saveTicket(ticket);
		return "redirect:/tickets";

	}

//	for editing ticket

	@GetMapping("/tickets/edit/{id}")
	public String updateTicketButtonClicked(@PathVariable Long id, Model model) {

		Ticket selectedTicket = ticketService.getTicketById(id);
		model.addAttribute("ticket", selectedTicket);

		return "edit_ticket";

	}

	@PostMapping("/tickets/{id}")
	public String submitButtonClickedForUpdateTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket ticket) {

		Ticket existingTicketObj = ticketService.getTicketById(id);
		existingTicketObj.setTicketTitle(ticket.getTicketTitle());
		existingTicketObj.setTicketShortDescription(ticket.getTicketShortDescription());
		existingTicketObj.setTicketCreatedOn(ticket.getTicketCreatedOn());

		ticketService.updateTicket(ticket);
		return "redirect:/tickets";
	}

	// Edit ticket - End

//	for deleting ticket

	@GetMapping("/tickets/{id}")
	public String deleteTicket(@PathVariable Long id) {

		ticketService.deleteById(id);
		return "redirect:/tickets";
	}

//	for viewing 
	@GetMapping("/tickets/view/{id}")
	public String viewTicket(@PathVariable Long id, Model model) {

		Ticket selectedTicket = ticketService.getTicketById(id);
		model.addAttribute("ticket", selectedTicket);
		return "view_ticket";

	}

//	for Search 

	@GetMapping(path = { "/tickets/search" })
	public String home(Ticket ticket, Model model, String keyword) {
		if (keyword != null) {
			List<Ticket> list = ticketService.getByKeyword(keyword);

			model.addAttribute("tickets", list);

		} else {

			List<Ticket> list = ticketService.listTickets();
			model.addAttribute("tickets", list);
		}
		return "tickets";
	}

}
