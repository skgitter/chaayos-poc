package com.sun.chaayos.services;

import java.util.List;

import com.sun.chaayos.model.Ticket;

public interface TicketService {
	public Ticket addTicket(Ticket ticket) throws Exception;
	public Boolean assignTicket(long empId, long ticketId) throws Exception;
	public Ticket getTicketById(long id) throws Exception;
	public List<Ticket> getTicketList() throws Exception;
	public List<Ticket> getTicketListByEmployee(long empId) throws Exception;
	public List<Ticket> getTicketListByDepartment(long departId) throws Exception;
	public boolean deleteTicket(long id) throws Exception;
}