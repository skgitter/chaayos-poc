package com.sun.chaayos.services;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.chaayos.model.Department;
import com.sun.chaayos.model.Employee;
import com.sun.chaayos.model.Ticket;

public class TicketServiceImpl implements TicketService{

	
	@Autowired
	SessionFactory sessionFactory;
	
	Session session = null;
	Transaction tx = null;
	
	@Override
	public Ticket addTicket(Ticket ticket) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(ticket);
		tx.commit();
		session.close();
		return ticket;
	}

	@Override
	public Ticket getTicketById(long id) throws Exception {
		session = sessionFactory.openSession();
		Ticket ticket = (Ticket) session.load(Ticket.class,
				new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Ticket> ticketList = session.createCriteria(Ticket.class).list();
		tx.commit();
		session.close();
		return ticketList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketListByEmployee(long empId) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Ticket.class);
		criteria.add(Restrictions.eq("employee_id", empId));
		List<Ticket> ticketList = criteria.list();
		session.close();
		return ticketList;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Ticket> getTicketListByDepartment(long departmentId) throws Exception {
		session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.eq("department_id", departmentId));
		List<Employee> empList = criteria.list();
		ArrayList empIdArrList = new ArrayList<>();
		ListIterator<Employee> lItr = empList.listIterator();
		while(lItr.hasNext()){
			empIdArrList.add(lItr.next().getId());
		}
		List<Ticket> tickets = session.createQuery("SELECT ticket_id,summary, description, type, created_at FROM ticket t WHERE i.id IN :ids")
				.setParameter("ids", empList).list();
		return tickets;
	}
	
	@Override
	public boolean deleteTicket(long id) throws Exception {
		return false;
	}

	@Override
	public Boolean assignTicket(long empId, long ticketId) throws Exception {
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Ticket ticket = (Ticket) session.load(Ticket.class,
					new Long(ticketId));
			Employee e = new Employee();
			e.setId(empId);
			ticket.setAssignedTo(e);
			session.save(ticket);
			tx.commit();
			session.close();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
