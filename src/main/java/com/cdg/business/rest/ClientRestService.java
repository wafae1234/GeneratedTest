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

import com.cdg.business.dto.ClientDTO;
import com.cdg.business.orchestration.ClientOrchestration;

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
public class ClientRestService {
	
	/**
	 * On déclare un objet de la classe ClientOrchestration qui va lui meme faire appel à la couche ClientServiceImpl
	 * pour alimenter les fonction ci dessous
	 */
	@Autowired
	private ClientOrchestration clientOrchestration;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	
	
	/**
	 * cette fonction nous retourne la liste des clients.
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/clients")
	public List<ClientDTO> getClients(){
		return clientOrchestration.getClients();
	}
	
	/**
	 * cette fonction nous retourne le client qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/clients/{id}")
	public ClientDTO getClient(@PathVariable Long id){
		return clientOrchestration.getClient(id);
	}
	
	/**
	 * cette fonction prend un client comme argument et puis elle le stock dans la base de donnée.
	 * @param client
	 * @return
	 */
	@PreAuthorize("hasAuthority('ADD')")
	@PostMapping(value="/clients")
	public ClientDTO saveClient(@RequestBody ClientDTO clientDTO){
		return clientOrchestration.saveClient(clientDTO);
	}
	
	/**
	 * cette fonction supprime le client qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('DELETE')")
	@DeleteMapping(value="/clients/{id}")
	public Boolean deleteClient(@PathVariable Long id){
		clientOrchestration.deleteClient(id);
		return true;
	}
	
	/**
	 * cette fonction fait la mise à jour d'un client
	 * @param id
	 * @param client
	 * @return
	 */
	@PreAuthorize("hasAuthority('UPDATE')")
	@PutMapping(value="/clients/{id}")
	public ClientDTO updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
		return clientOrchestration.updateClient(id, clientDTO);
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs clients selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForClientsWithOnekeyword")
	public Page<ClientDTO> searchForClientsWithOnekeyword( 
			@RequestParam(name="keyword",defaultValue = "chaali")String keyword,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			){
		return clientOrchestration.searchClient(keyword, page, size, column);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/searchForClientsWithTwoKeywords")
	public Page<ClientDTO> searchForClientsWithTwoKeywords( 
			@RequestParam(name="keyword1",defaultValue = "1000-01-01")String keyword1,
			@RequestParam(name="keyword2",defaultValue = "9999-12-31")String keyword2,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column",defaultValue = "nom")String column
			) throws ParseException{
		return clientOrchestration.searchTwoKeywords(keyword1, keyword2, page, size, column);
	}
	
	@PreAuthorize("hasAuthority('READ')")
	@GetMapping(value="/getPageOfClients")
	public Page<ClientDTO> getPageOfClients( 
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size
			){
		return clientOrchestration.getPageofClients(page, size);
	}
	
	
	

}
