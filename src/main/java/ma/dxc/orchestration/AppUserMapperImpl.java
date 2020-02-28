package ma.dxc.orchestration;

import org.modelmapper.ModelMapper;

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

public class AppUserMapperImpl implements AppUserMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public AppUserDTO toAppUserDTO(AppUser appUser) {
		return modelMapper.map(appUser, AppUserDTO.class);
	}

	@Override
	public AppUser toAppUser(AppUserDTO appUserDto) {
		return modelMapper.map(appUserDto, AppUser.class);
	}

}
