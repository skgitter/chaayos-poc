package com.sun.chaayos.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.chaayos.model.Ticket;
import com.sun.chaayos.services.TicketService;

@Controller
@RequestMapping("/ticket")
public class TicketController {
	@Autowired
	TicketService ticketServices;
	static final Logger logger = Logger.getLogger(TicketController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.GET, params = {"emp_id", "depart_id"})
	public @ResponseBody
	List<Ticket> getEmployees(@RequestParam(value="emp_id") String empId, @RequestParam(value="depart_id") String departId){
		List<Ticket> emploList = null;
		try{
			if(empId == null && departId == null){
				emploList = ticketServices.getTicketList();
			}
			
			if(empId != null){
				emploList = ticketServices.getTicketListByEmployee(Long.parseLong(empId));
			}
			
			if(departId != null){
				emploList = ticketServices.getTicketListByDepartment(Long.parseLong(empId));
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return emploList;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	Ticket addTicket(@RequestBody Ticket ticket) {
		try {
			return ticketServices.addTicket(ticket);
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/assign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, params={"emp_id"})
	public @ResponseBody
	Boolean addTicket(@RequestParam(value="emp_id") String empId, @RequestParam(value="ticket_id") String ticketId ) {
		try {
			return ticketServices.assignTicket(Long.parseLong(empId), Long.parseLong(ticketId)); 
		} catch (Exception e) {
			return false;
		}
	}
}