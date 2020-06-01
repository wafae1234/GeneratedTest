package com.cdg.business.rest;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdg.business.dto.AuditDTO;
import com.cdg.business.orchestration.AuditOrchestration;

@RestController
@CrossOrigin("*")
@PreAuthorize("isAuthenticated()") 
public class AuditRestService {
	
	@Autowired
	private AuditOrchestration auditOrchestration;

	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/audits")
	public List<AuditDTO> getAppUsers(){
		System.out.println(auditOrchestration.getAudits());
		return auditOrchestration.getAudits();
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/getPageOfAudits")
	public Page<AuditDTO> getPageOfAudits( 
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size
			){
		return auditOrchestration.getPageOfAudits(page, size);
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs audits selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForAuditsWithOnekeyword")
	public Page<AuditDTO> searchForAuditsWithOnekeyword( 
			@RequestParam(name="keyword",defaultValue = "chaali")String keyword,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			){
		return auditOrchestration.searchAudit(keyword, page, size, column);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForAuditsWithTwoKeywords")
	public Page<AuditDTO> searchForAuditsWithTwoKeywords( 
			@RequestParam(name="keyword1",defaultValue = "1000-01-01")String keyword1,
			@RequestParam(name="keyword2",defaultValue = "9999-12-31")String keyword2,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			) throws ParseException{
		return auditOrchestration.searchTwoKeywords(keyword1, keyword2, page, size, column);
	}
}
