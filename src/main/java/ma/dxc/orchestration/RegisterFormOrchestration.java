package ma.dxc.orchestration;

import ma.dxc.model.AppUser;
import ma.dxc.service.RegisterFormService;

import org.springframework.beans.factory.annotation.Autowired;

import ma.dxc.dto.AppUserDTO;
import ma.dxc.dto.RegisterFormDTO;

public class RegisterFormOrchestration {
	
	@Autowired
	private RegisterFormService registerFormService;
	
	
	public AppUserDTO register(RegisterFormDTO userFormDto) {
		
		AppUser appUser = registerFormService.register(RegisterFormMapper.INSTANCE.toRegisterForm(userFormDto));
		
		return AppUserMapper.INSTANCE.toAppUserDTO(appUser);
	}
}
