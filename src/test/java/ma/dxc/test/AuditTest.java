package ma.dxc.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import ma.dxc.dto.AuditDTO;
import ma.dxc.model.AppRole;
import ma.dxc.model.AppUser;
import ma.dxc.model.Contact;
import ma.dxc.model.Permission;
import ma.dxc.orchestration.AuditOrchestration;
import ma.dxc.service.ContactServiceImpl;
import ma.dxc.service.PermissionServiceImpl;
import ma.dxc.service.RoleServiceImpl;
import ma.dxc.service.UserServiceImpl;

@SpringBootTest
public class AuditTest {
	@Autowired
	private AuditOrchestration auditOrchestration;
	
	@Autowired
	ContactServiceImpl contacService; 
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	RoleServiceImpl roleService;
	
	@Autowired
	PermissionServiceImpl permissionService; 
	
	/*@Test
	void testSearchForAuditsWithTwoKeywords() {
		Page<AuditDTO> audits = auditOrchestration.searchTwoKeywords("2020-05-04", "2020-05-04", 0, 5, "date");
		
		assertThat(audits).isNotNull();
		//assertThat(audits.getContent().get(0)).isInstanceOf(AuditDTO);
		assertThat(audits.getContent().get(0).getId()).isEqualTo(323);
		//assertThat(audits.)
	}*/
	
	/*@Test
	void testUpdateUser() {
		Long userId = Long.valueOf(331);
		Long roleId = Long.valueOf(414);
		AppUser appUser = userService.findOne(userId);
		Collection<AppRole> roles = new ArrayList<>();
		roles = appUser.getRoles();
		AppRole appRole = roleService.findOne(roleId);
		roles.add(appRole);
		appUser.setRoles(roles);
		userService.update(userId, appUser);
	}*/
	
	@Test
	void testUpdateRole() {
		Long permissionId = Long.valueOf(1);
		Long roleId = Long.valueOf(414);
		AppRole appRole = roleService.findOne(roleId);
		Collection<Permission> permissions = new ArrayList<>();
		permissions = appRole.getPermissions();
		Permission permission = permissionService.findOne(permissionId);
		permissions.add(permission);
		appRole.setPermissions(permissions);
		roleService.update(roleId, appRole);
	}

}
