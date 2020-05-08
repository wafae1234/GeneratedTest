package ma.dxc.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import ma.dxc.service.audit.Operation;


@Entity
public class Audit {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String user;
	
	@DateTimeFormat
	private Date date;
	
	@Enumerated(EnumType.STRING)
	private Operation operation;
	
	private String changes;

	public Audit(String user, Operation operation, String changes, Date date) {
		super();
		this.user = user;
		this.operation = operation;
		this.changes = changes;
		this.date = date;
	}

	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Audit(Long id, String user, Date date, Operation operation, String changes) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.operation = operation;
		this.changes = changes;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}

	public String getChanges() {
		return changes;
	}

	public void setChanges(String changes) {
		this.changes = changes;
	}

	public Long getId() {
		return id;
	}
	
}
