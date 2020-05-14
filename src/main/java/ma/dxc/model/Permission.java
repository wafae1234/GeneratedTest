package ma.dxc.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import ma.dxc.service.EntityListener.PermissionEntityListener;

@Entity
@EntityListeners(PermissionEntityListener.class)
public class Permission {
	@Id @GeneratedValue
	private Long id;
	private String permissionName;
	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Permission(Long id, String permissionName) {
		super();
		this.id = id;
		this.permissionName = permissionName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	@Override
	public String toString() {
		return "Permission [id=" + id + ", permissionName=" + permissionName + "]";
	}
	
	
}
