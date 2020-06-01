package com.cdg.business.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.cdg.business.dto.AppUserDTO;
import com.cdg.business.model.AppUser;

//@Mapper
public interface AppUserMapper {
	
public AppUserDTO toAppUserDTO(AppUser appUser);
	
	public List<AppUserDTO> toAppUserDTOs(List<AppUser> appUsers);
	
	public AppUser toAppUser(AppUserDTO appUserDTO);
	
	public Page<AppUserDTO> toAppUserDTOsPageable(Page<AppUser> appUsers);
	
	AppUserMapper INSTANCE = Mappers.getMapper( AppUserMapper.class );
}
