package ma.dxc.dto;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;


import ma.dxc.model.Permission;

public class AppRoleDTO {
	
	private Long id;
	private String roleName;
	private Collection<Permission> permissions = new ArrayList<>();
	
=======
import java.util.Objects;

public class AppRoleDTO {
	private Long id;
	private String roleName;
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
	public AppRoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
<<<<<<< HEAD
	public AppRoleDTO(Long id, String roleName, Collection<Permission> permissions) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.permissions = permissions;
=======
	public AppRoleDTO(String roleName) {
		super();
		this.roleName = roleName;
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
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
<<<<<<< HEAD
	public Collection<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Collection<Permission> permissions) {
		this.permissions = permissions;
	}
=======
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
	
	
	
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
}
