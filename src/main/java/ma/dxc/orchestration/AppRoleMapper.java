package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.AppRoleDTO;
import ma.dxc.model.AppRole;

@Mapper
public interface AppRoleMapper {
	
	public AppRoleDTO toAppRoleDTO(AppRole appRole);
	
	public List<AppRoleDTO> toAppRoleDTOs(List<AppRole> appRoles);
	
	public AppRole toAppRole(AppRoleDTO appRoleDTO);
	
	AppRoleMapper INSTANCE = Mappers.getMapper( AppRoleMapper.class );
}
