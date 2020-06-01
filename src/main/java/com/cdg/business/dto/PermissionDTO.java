package com.cdg.business.dto;

public class PermissionDTO {
	
	private Long id;
	private String permissionName;
	public PermissionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PermissionDTO(Long id, String permissionName) {
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
}
