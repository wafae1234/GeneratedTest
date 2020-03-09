package ma.dxc.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
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

import ma.dxc.dto.ContactDTO;
import ma.dxc.orchestration.ContactOrchestration;

import ma.dxc.security.AuthenticationRequest;
import ma.dxc.security.AuthenticationResponse;
import ma.dxc.security.JwtUtil;
import ma.dxc.service.MyUserDetailsService;

/**
 * C'est la classe responsable de l'API Rest qui joue le role de l'intermédiaire entre la partie backend et la partie 
 * frontend.
 * @author dchaa
 *
 */
@RestController
@CrossOrigin("*")
public class ContactRestService {
	
	/**
	 * On déclare un objet de la classe ContactOrchestration qui va lui meme faire appel à la couche ContactServiceImpl
	 * pour alimenter les fonction ci dessous
	 */
	@Autowired
	private ContactOrchestration contactOrchestration;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	
	
	
	/**
	 * cette fonction nous retourne la liste des contacts.
	 * @return
	 */
	@GetMapping(value="/contacts")
	public List<ContactDTO> getContacts(){
		return contactOrchestration.getContacts();
	}
	
	/**
	 * cette fonction nous retourne le contact qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	@GetMapping(value="/contacts/{id}")
	public ContactDTO getContact(@PathVariable Long id){
		return contactOrchestration.getContact(id);
	}
	
	/**
	 * cette fonction prend un contact comme argument et puis elle le stock dans la base de donnée.
	 * @param contact
	 * @return
	 */
	@PostMapping(value="/contacts")
	public ContactDTO saveContact(@RequestBody ContactDTO contactDTO){
		return contactOrchestration.saveContact(contactDTO);
	}
	
	/**
	 * cette fonction supprime le contact qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	@DeleteMapping(value="/contacts/{id}")
	public Boolean deleteContact(@PathVariable Long id){
		contactOrchestration.deleteContact(id);
		return true;
	}
	
	/**
	 * cette fonction fait la mise à jour d'un contact
	 * @param id
	 * @param contact
	 * @return
	 */
	@PutMapping(value="/contacts/{id}")
	public ContactDTO updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO){
		return contactOrchestration.updateContact(id, contactDTO);
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs contacts selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value="/chercherContact")
	public Page<ContactDTO> searchContact( 
			@RequestParam(name="mc",defaultValue = "")String mc,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column")String column
			){
		return contactOrchestration.searchContact(mc, page, size, column);
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
 
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	

}
