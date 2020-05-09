package ma.dxc.orchestration;

<<<<<<< HEAD
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
=======
import org.modelmapper.ModelMapper;
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

public class AppUserMapperImpl implements AppUserMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
<<<<<<< HEAD
	public AppUserDTO toAppUserDTO(AppUser AppUser) {
		return modelMapper.map(AppUser, AppUserDTO.class);
	}

	@Override
	public List<AppUserDTO> toAppUserDTOs(List<AppUser> AppUsers) {
		
		Type listType = new TypeToken<List<AppUserDTO>>(){}.getType();
		List<AppUserDTO> AppUserDTOs = modelMapper.map(AppUsers,listType);
		
		return AppUserDTOs;
	}

	@Override
	public AppUser toAppUser(AppUserDTO AppUserDTO) {
		return modelMapper.map(AppUserDTO, AppUser.class);
	}

	@Override
	public Page<AppUserDTO> toAppUserDTOsPageable(Page<AppUser> appUsers) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<Page<AppUserDTO>>(){}.getType();
		Page<AppUserDTO> AppUserDTOspageable = modelMapper.map(appUsers,listType);
		return AppUserDTOspageable;
=======
	public AppUserDTO toAppUserDTO(AppUser appUser) {
		return modelMapper.map(appUser, AppUserDTO.class);
	}

	@Override
	public AppUser toAppUser(AppUserDTO appUserDto) {
		return modelMapper.map(appUserDto, AppUser.class);
>>>>>>> 03c545f918a86ab8cc3fe1130c64efb73864dc5f
	}

}
