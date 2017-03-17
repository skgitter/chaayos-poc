package com.sun.chaayos.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name = "employee")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private Employee managerEmployeeId;
	private Department employeeDepartment;
	private Set<Ticket> ticket;
	
	@Id
	@GeneratedValue
	@Column(name = "employee_id")
	public long getId() {
		return id;
	}

	@OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
	public Set<Ticket> getTickets() {
		return ticket;
	}

	public void setTickets(Set<Ticket> ticket) {
		this.ticket = ticket;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name="department_id")
	public Department getEmployeeDepartment() {
		return employeeDepartment;
	}

	public void setEmployeeDepartment(Department employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}

	@JoinColumn(name="employee_id")
	public Employee getManagerEmployeeId() {
		return managerEmployeeId;
	}

	public void setManagerEmployeeId(Employee managerEmployeeId) {
		this.managerEmployeeId = managerEmployeeId;
	}
}