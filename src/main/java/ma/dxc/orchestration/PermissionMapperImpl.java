package ma.dxc.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;

import ma.dxc.dto.PermissionDTO;
import ma.dxc.model.Permission;

public class PermissionMapperImpl implements PermissionMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public PermissionDTO toPermissionDTO(Permission Permission) {
		return modelMapper.map(Permission, PermissionDTO.class);
	}

	@Override
	public List<PermissionDTO> toPermissionDTOs(List<Permission> Permissions) {
		
		Type listType = new TypeToken<List<PermissionDTO>>(){}.getType();
		List<PermissionDTO> PermissionDTOs = modelMapper.map(Permissions,listType);
		
		return PermissionDTOs;
	}

	@Override
	public Permission toPermission(PermissionDTO PermissionDTO) {
		return modelMapper.map(PermissionDTO, Permission.class);
	}

	@Override
	public Page<PermissionDTO> toPermissionDTOsPageable(Page<Permission> Permissions) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<Page<PermissionDTO>>(){}.getType();
		Page<PermissionDTO> PermissionDTOsPageable = modelMapper.map(Permissions,listType);
		return PermissionDTOsPageable;
	}

}
