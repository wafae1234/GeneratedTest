package ma.dxc.dto;

import java.util.Objects;

public class AppRoleDTO {
	private Long id;
	private String roleName;
	public AppRoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppRoleDTO(String roleName) {
		super();
		this.roleName = roleName;
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
	@Override
	public String toString() {
		return "AppRoleDTO [id=" + id + ", roleName=" + roleName + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, roleName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppRoleDTO other = (AppRoleDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(roleName, other.roleName);
	}
	
	
	
}
