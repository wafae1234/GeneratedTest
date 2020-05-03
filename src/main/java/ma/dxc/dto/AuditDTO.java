package ma.dxc.dto;

import ma.dxc.service.audit.Operation;

public class AuditDTO {
	
	private Long id;
	private String user;
	private String date;
	private Operation operation;
	private String changes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	public AuditDTO(Long id, String user, String date, Operation operation, String changes) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.operation = operation;
		this.changes = changes;
	}
	@Override
	public String toString() {
		return "AuditDTO [id=" + id + ", user=" + user + ", date=" + date + ", operation=" + operation + ", changes="
				+ changes + "]";
	}
	public AuditDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
