package ma.dxc.dto;

import java.util.ArrayList;
import java.util.Collection;


import ma.dxc.model.Permission;

public class AppRoleDTO {
	
	private Long id;
	private String roleName;
	private Collection<Permission> permissions = new ArrayList<>();
	
	public AppRoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppRoleDTO(Long id, String roleName, Collection<Permission> permissions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Collection<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}
}
