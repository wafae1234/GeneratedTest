package com.cdg.business.orchestration;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cdg.business.dto.RegisterFormDTO;
import com.cdg.business.model.RegisterForm;

//@Mapper
public interface RegisterFormMapper {
	
	public RegisterFormDTO toRegisterDTO(RegisterForm registerForm);
	
	public RegisterForm toRegisterForm(RegisterFormDTO registerFormDto);
	
	RegisterFormMapper INSTANCE = Mappers.getMapper( RegisterFormMapper.class );
	
}
