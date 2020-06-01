package com.cdg.business.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;

import com.cdg.business.dto.AppUserDTO;
import com.cdg.business.model.AppUser;

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

	@Override
	public Page<AppUserDTO> toAppUserDTOsPageable(Page<AppUser> appUsers) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<Page<AppUserDTO>>(){}.getType();
		Page<AppUserDTO> AppUserDTOspageable = modelMapper.map(appUsers,listType);
		return AppUserDTOspageable;
	}

}
