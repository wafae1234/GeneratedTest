package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import ma.dxc.dto.AppRoleDTO;
import ma.dxc.model.AppRole;

@Mapper
public interface AppRoleMapper {
	
	public AppRoleDTO toAppRoleDTO(AppRole appRole);
	
	public List<AppRoleDTO> toAppRoleDTOs(List<AppRole> appRoles);
	
	public AppRole toAppRole(AppRoleDTO appRoleDTO);
	
	public Page<AppRoleDTO> toAppRoleDTOsPageable(Page<AppRole> appRoles);
	
	AppRoleMapper INSTANCE = Mappers.getMapper( AppRoleMapper.class );
}
