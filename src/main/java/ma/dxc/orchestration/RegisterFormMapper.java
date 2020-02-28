package ma.dxc.orchestration;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.RegisterFormDTO;
import ma.dxc.model.RegisterForm;

@Mapper
public interface RegisterFormMapper {
	
	public RegisterFormDTO toRegisterDTO(RegisterForm registerForm);
	
	public RegisterForm toRegisterForm(RegisterFormDTO registerFormDto);
	
	RegisterFormMapper INSTANCE = Mappers.getMapper( RegisterFormMapper.class );
	
}
