package ma.dxc.dto;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Collection;


import ma.dxc.model.AppRole;

public class AppUserDTO {
	
	private Long id;
	
	private String username;
	private String password;
	
	private Collection<AppRole> roles = new ArrayList<>();

	public AppUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppUserDTO(Long id, String username, String password, Collection<AppRole> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(Collection<AppRole> roles) {
		this.roles = roles;
	}
=======
import java.util.Objects;

public class AppUserDTO {
	private Long id;
	private String username;
	private String password;
	public AppUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppUserDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AppUserDTO [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUserDTO other = (AppUserDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}
	
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
}
