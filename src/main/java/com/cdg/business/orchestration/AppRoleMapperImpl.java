package com.cdg.business.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;

import com.cdg.business.dto.AppRoleDTO;
import com.cdg.business.model.AppRole;

public class AppRoleMapperImpl implements AppRoleMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public AppRoleDTO toAppRoleDTO(AppRole AppRole) {
		return modelMapper.map(AppRole, AppRoleDTO.class);
	}

	@Override
	public List<AppRoleDTO> toAppRoleDTOs(List<AppRole> AppRoles) {
		
		Type listType = new TypeToken<List<AppRoleDTO>>(){}.getType();
		List<AppRoleDTO> AppRoleDTOs = modelMapper.map(AppRoles,listType);
		
		return AppRoleDTOs;
	}

	@Override
	public AppRole toAppRole(AppRoleDTO AppRoleDTO) {
		return modelMapper.map(AppRoleDTO, AppRole.class);
	}

	@Override
	public Page<AppRoleDTO> toAppRoleDTOsPageable(Page<AppRole> appRoles) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<Page<AppRoleDTO>>(){}.getType();
		Page<AppRoleDTO> AppRoleDTOsPageable = modelMapper.map(appRoles,listType);
		return AppRoleDTOsPageable;
	}

}
