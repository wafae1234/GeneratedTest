package com.cdg.business.orchestration;

import org.modelmapper.ModelMapper;

import com.cdg.business.dto.RegisterFormDTO;
import com.cdg.business.model.RegisterForm;

public class RegisterFormMapperImpl implements RegisterFormMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public RegisterFormDTO toRegisterDTO(RegisterForm registerForm) {
		return modelMapper.map(registerForm,RegisterFormDTO.class );
	}

	@Override
	public RegisterForm toRegisterForm(RegisterFormDTO registerFormDto) {
		return modelMapper.map(registerFormDto, RegisterForm.class);
	}

}
