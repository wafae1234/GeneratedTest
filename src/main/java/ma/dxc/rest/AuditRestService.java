package ma.dxc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.dxc.dto.AuditDTO;
import ma.dxc.orchestration.AuditOrchestration;

@RestController
@CrossOrigin("*")
@PreAuthorize("isAuthenticated()") 
public class AuditRestService {
	
	@Autowired
	private AuditOrchestration auditOrchestartion;

	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/audits")
	public List<AuditDTO> getAppUsers(){
		System.out.println(auditOrchestartion.getAudits());
		return auditOrchestartion.getAudits();
	}
}
