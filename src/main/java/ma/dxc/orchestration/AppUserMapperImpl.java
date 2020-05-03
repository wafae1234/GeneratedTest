package ma.dxc.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import ma.dxc.dto.AppUserDTO;
import ma.dxc.model.AppUser;

public class AppUserMapperImpl implements AppUserMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
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

}
