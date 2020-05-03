package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

@Mapper
public interface AppUserMapper {
	
public AppUserDTO toAppUserDTO(AppUser appUser);
	
	public List<AppUserDTO> toAppUserDTOs(List<AppUser> appUsers);
	
	public AppUser toAppUser(AppUserDTO appUserDTO);
	
	AppUserMapper INSTANCE = Mappers.getMapper( AppUserMapper.class );
}
