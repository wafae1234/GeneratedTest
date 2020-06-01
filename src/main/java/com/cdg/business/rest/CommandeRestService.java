package com.cdg.business.rest;

import java.text.ParseException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdg.business.dto.CommandeDTO;
import com.cdg.business.orchestration.CommandeOrchestration;

import com.cdg.business.security.AuthenticationRequest;
import com.cdg.business.security.AuthenticationResponse;
import com.cdg.business.security.JwtUtil;
import com.cdg.business.service.MyUserDetailsService;

/**
 * C'est la classe responsable de l'API Rest qui joue le role de l'intermédiaire entre la partie backend et la partie 
 * frontend.
 * @author dchaa
 *
 */
@RestController
@CrossOrigin("*")
@PreAuthorize("isAuthenticated()") 
public class CommandeRestService {
	
	/**
	 * On déclare un objet de la classe CommandeOrchestration qui va lui meme faire appel à la couche CommandeServiceImpl
	 * pour alimenter les fonction ci dessous
	 */
	@Autowired
	private CommandeOrchestration commandeOrchestration;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	
	
	/**
	 * cette fonction nous retourne la liste des commandes.
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/commandes")
	public List<CommandeDTO> getCommandes(){
		return commandeOrchestration.getCommandes();
	}
	
	/**
	 * cette fonction nous retourne le commande qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/commandes/{id}")
	public CommandeDTO getCommande(@PathVariable Long id){
		return commandeOrchestration.getCommande(id);
	}
	
	/**
	 * cette fonction prend un commande comme argument et puis elle le stock dans la base de donnée.
	 * @param commande
	 * @return
	 */
	@PreAuthorize("hasAuthority('ADD')")
	@PostMapping(value="/commandes")
	public CommandeDTO saveCommande(@RequestBody CommandeDTO commandeDTO){
		return commandeOrchestration.saveCommande(commandeDTO);
	}
	
	/**
	 * cette fonction supprime le commande qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('DELETE')")
	@DeleteMapping(value="/commandes/{id}")
	public Boolean deleteCommande(@PathVariable Long id){
		commandeOrchestration.deleteCommande(id);
		return true;
	}
	
	/**
	 * cette fonction fait la mise à jour d'un commande
	 * @param id
	 * @param commande
	 * @return
	 */
	@PreAuthorize("hasAuthority('UPDATE')")
	@PutMapping(value="/commandes/{id}")
	public CommandeDTO updateCommande(@PathVariable Long id, @RequestBody CommandeDTO commandeDTO){
		return commandeOrchestration.updateCommande(id, commandeDTO);
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs commandes selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForCommandesWithOnekeyword")
	public Page<CommandeDTO> searchForCommandesWithOnekeyword( 
			@RequestParam(name="keyword",defaultValue = "chaali")String keyword,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			){
		return commandeOrchestration.searchCommande(keyword, page, size, column);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForCommandesWithTwoKeywords")
	public Page<CommandeDTO> searchForCommandesWithTwoKeywords( 
			@RequestParam(name="keyword1",defaultValue = "1000-01-01")String keyword1,
			@RequestParam(name="keyword2",defaultValue = "9999-12-31")String keyword2,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			) throws ParseException{
		return commandeOrchestration.searchTwoKeywords(keyword1, keyword2, page, size, column);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/getPageOfCommandes")
	public Page<CommandeDTO> getPageOfCommandes( 
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size
			){
		return commandeOrchestration.getPageofCommandes(page, size);
	}
	
	
	

}
