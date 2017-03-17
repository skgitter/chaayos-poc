package com.sun.chaayos.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

@Entity
@Table(name ="ticket")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ticket {
	private int ticketId;
	private String summary;
	private String description;
	private String type;
	private Employee createdBy;
	private Date creationTime;
	private Employee assignedTo;
	private Date assinmentTime;
	private Employee closedBy;
	private Date closingTime;
	
	@Id
	@GeneratedValue
	@Column(name = "ticket_id")
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	
	@Column(name = "summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Column(name = "type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	public Employee getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Employee createdBy) {
		this.createdBy = createdBy;
	}
	
	
	@Column(name="created_at", columnDefinition="DATETIME")
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	
	
	@ManyToOne
	@JoinColumn(name="assigned_to")
	public Employee getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(Employee assignedTo) {
		this.assignedTo = assignedTo;
	}
	

	@Column(name="assignment_time", columnDefinition="DATETIME")
	public Date getAssinmentTime() {
		return assinmentTime;
	}
	public void setAssinmentTime(Date assinmentTime) {
		this.assinmentTime = assinmentTime;
	}
	
	@ManyToOne
	@JoinColumn(name="closed_by")
	public Employee getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(Employee closedBy) {
		this.closedBy = closedBy;
	}
	
	
	@Column(name="closing_time", columnDefinition="DATETIME")
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
}