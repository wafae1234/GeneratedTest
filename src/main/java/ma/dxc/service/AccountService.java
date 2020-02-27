package ma.dxc.service;

import ma.dxc.model.AppRole;
import ma.dxc.model.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUsername(String username);
}
