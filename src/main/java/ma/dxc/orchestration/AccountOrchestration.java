package ma.dxc.orchestration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.dxc.dto.AppRoleDTO;
import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppRole;
import ma.dxc.model.AppUser;
import ma.dxc.repository.RoleRepository;
import ma.dxc.repository.UserRepository;
import ma.dxc.service.AccountServiceImpl;

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
