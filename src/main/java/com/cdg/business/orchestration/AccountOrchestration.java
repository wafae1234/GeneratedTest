package com.cdg.business.orchestration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdg.business.dto.AppRoleDTO;
import com.cdg.business.dto.AppUserDTO;
import com.cdg.business.model.AppRole;
import com.cdg.business.model.AppUser;
import com.cdg.business.repository.RoleRepository;
import com.cdg.business.repository.UserRepository;
import com.cdg.business.service.AccountServiceImpl;

@Service
public class AccountOrchestration {
	
	@Autowired
	AccountServiceImpl accountServiceImpl;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	
	public AppUserDTO saveUser(AppUserDTO userDTO) {
		AppUser user = AppUserMapper.INSTANCE.toAppUser(userDTO);
		return AppUserMapper.INSTANCE.toAppUserDTO(accountServiceImpl.saveUser(user));
	}

	
	public AppRoleDTO saveRole(AppRoleDTO roleDTO) {
		AppRole role = AppRoleMapper.INSTANCE.toAppRole(roleDTO);
		return AppRoleMapper.INSTANCE.toAppRoleDTO(role);
	}

	public void addRoleToUser(String username, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
	}

	public AppUserDTO findUserByUsername(String username) {
		
		return AppUserMapper.INSTANCE.toAppUserDTO(accountServiceImpl.findUserByUsername(username));
	}
}
