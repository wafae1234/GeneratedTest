package ma.dxc.orchestration;

<<<<<<< HEAD
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
=======
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

@Mapper
<<<<<<< HEAD
public interface AppUserMapper {
	
public AppUserDTO toAppUserDTO(AppUser appUser);
	
	public List<AppUserDTO> toAppUserDTOs(List<AppUser> appUsers);
	
	public AppUser toAppUser(AppUserDTO appUserDTO);
	
	public Page<AppUserDTO> toAppUserDTOsPageable(Page<AppUser> appUsers);
	
	AppUserMapper INSTANCE = Mappers.getMapper( AppUserMapper.class );
=======
public interface AppUserMapper{
	
	public AppUserDTO toAppUserDTO(AppUser appUser);
	
	public AppUser toAppUser(AppUserDTO appUserDto);
	
	AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);
	
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
}
