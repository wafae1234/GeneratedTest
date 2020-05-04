package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;


import ma.dxc.dto.PermissionDTO;
import ma.dxc.model.Permission;

@Mapper
public interface PermissionMapper {
	
	public PermissionDTO toPermissionDTO(Permission Permission);
	
	public List<PermissionDTO> toPermissionDTOs(List<Permission> Permissions);
	
	public Permission toPermission(PermissionDTO PermissionDTO);
	
	public Page<PermissionDTO> toPermissionDTOsPageable(Page<Permission> Permissions);
	
	PermissionMapper INSTANCE = Mappers.getMapper( PermissionMapper.class );
	
	
}
