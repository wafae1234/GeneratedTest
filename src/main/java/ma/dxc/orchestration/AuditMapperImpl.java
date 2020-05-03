package ma.dxc.orchestration;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import ma.dxc.dto.AuditDTO;
import ma.dxc.model.Audit;

public class AuditMapperImpl implements AuditMapper {
	
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public AuditDTO toAuditDTO(Audit audit) {
		return modelMapper.map(audit, AuditDTO.class);
	}

	@Override
	public List<AuditDTO> toAuditDTOs(List<Audit> audits) {
		
		Type listType = new TypeToken<List<AuditDTO>>(){}.getType();
		List<AuditDTO> auditDTOs = modelMapper.map(audits,listType);
		
		return auditDTOs;
	}

	@Override
	public Audit toAudit(AuditDTO auditDTO) {
		return modelMapper.map(auditDTO, Audit.class);
	}

}
