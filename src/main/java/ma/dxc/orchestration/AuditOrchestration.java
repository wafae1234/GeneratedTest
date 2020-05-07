package ma.dxc.orchestration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ma.dxc.dto.AuditDTO;
import ma.dxc.model.Audit;
import ma.dxc.service.audit.AuditServiceImpl;

@Service
public class AuditOrchestration {
	
	/**
	 * On déclare un objet de la classe AuditServiceImpl qui va lui meme faire appel à la couche DAO
	 * afin d'interagir avec la base de données.
	 */
	@Autowired
	private AuditServiceImpl Auditservice;
	
	/**
	 * cette fonction nous retourne la liste des Audits.
	 * @return
	 */
	public List<AuditDTO> getAudits(){
		return AuditMapper.INSTANCE.toAuditDTOs(Auditservice.findAll());
	}
	
	/**
	 * cette fonction nous retourne le Audit qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	public AuditDTO getAudit(Long id){
		return AuditMapper.INSTANCE.toAuditDTO(Auditservice.findOne(id));
	}
	
	/**
	 * cette fonction prend un Audit comme argument et puis elle le stock dans la base de donnée.
	 * @param Audit
	 * @return
	 */
	public AuditDTO saveAudit(AuditDTO auditDTO){
		Audit audit = AuditMapper.INSTANCE.toAudit(auditDTO);
		audit = Auditservice.save(audit);
		return AuditMapper.INSTANCE.toAuditDTO(audit);
	}
	
	/**
	 * cette fonction fait la mise à jour d'un Audit
	 * @param id
	 * @param Audit
	 * @return
	 */
	public AuditDTO updateAudit(Long id, AuditDTO auditDTO){
		Audit audit = AuditMapper.INSTANCE.toAudit(auditDTO);
		auditDTO =  AuditMapper.INSTANCE.toAuditDTO(Auditservice.update(id, audit));
		return auditDTO;
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs Audits selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<AuditDTO> searchAudit( String mc,int page,int size,String column){
		Page<Audit> audits = Auditservice.search(mc, page, size, column);
		List<AuditDTO> auditDTOs = AuditMapper.INSTANCE.toAuditDTOs(audits.getContent());
		return new PageImpl<>(auditDTOs,PageRequest.of(page, size),audits.getTotalElements());
	}
	
	public Page<AuditDTO> searchTwoKeywords(String mc1, String mc2, int page, int size, String column){
		Page<Audit> audits = Auditservice.searchTwoKeywords(mc1, mc2, page, size, column);
		Page<AuditDTO> auditDTOs = AuditMapper.INSTANCE.toAuditDTOsPageable(audits);
		return auditDTOs;
	}
	
	public Page<AuditDTO> getPageOfAudits(int page,int size){
		Page<Audit> audits = Auditservice.findAllPageable(page, size);
		Page<AuditDTO> auditDTOs = AuditMapper.INSTANCE.toAuditDTOsPageable(audits);
		return auditDTOs;
	}

}
