package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import ma.dxc.dto.AuditDTO;
import ma.dxc.model.Audit;

public interface AuditMapper {
	
	public AuditDTO toAuditDTO(Audit audit);
	
	public List<AuditDTO> toAuditDTOs(List<Audit> audits);
	
	public Audit toAudit(AuditDTO auditDTO);
	
	public Page<AuditDTO> toAuditDTOsPageable(Page<Audit> audits);
	
	AuditMapper INSTANCE = Mappers.getMapper( AuditMapper.class );

}
