package com.cdg.business.orchestration;

import com.cdg.business.model.AppUser;
import com.cdg.business.service.RegisterFormService;

import org.springframework.beans.factory.annotation.Autowired;

import com.cdg.business.dto.AppUserDTO;
import com.cdg.business.dto.RegisterFormDTO;

public class RegisterFormOrchestration {
	
	@Autowired
	private RegisterFormService registerFormService;
	
	
	public AppUserDTO register(RegisterFormDTO userFormDto) {
		
		AppUser appUser = registerFormService.register(RegisterFormMapper.INSTANCE.toRegisterForm(userFormDto));
		
		return AppUserMapper.INSTANCE.toAppUserDTO(appUser);
	}
}
