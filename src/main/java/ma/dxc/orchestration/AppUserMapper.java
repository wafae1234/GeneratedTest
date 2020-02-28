package ma.dxc.orchestration;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

@Mapper
public interface AppUserMapper{
	
	public AppUserDTO toAppUserDTO(AppUser appUser);
	
	public AppUser toAppUser(AppUserDTO appUserDto);
	
	AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
	
}
