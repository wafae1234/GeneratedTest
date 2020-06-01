package com.cdg.business.service;

import com.cdg.business.model.AppRole;
import com.cdg.business.model.AppUser;

public interface AccountService {
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username, String roleName);
	public AppUser findUserByUsername(String username);
}
