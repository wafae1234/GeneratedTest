package ma.dxc.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.dxc.model.Contact;
import ma.dxc.repository.ContactRepository;
import ma.dxc.service.ContactServiceImpl;

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
	 * On déclare un objet de la classe ContactServiceImpl qui va lui meme faire appel à la couche DAO
	 * afin d'interagir avec la base de données.
	 */
	@Autowired
	private ContactServiceImpl contactservice;
	
	/**
	 * cette fonction nous retourne la liste des contacts.
	 * @return
	 */
	@RequestMapping(value="/contacts",method=RequestMethod.GET)
	public List<Contact> getContacts(){
		return contactservice.findAll();
	}
	
	/**
	 * cette fonction nous retourne le contact qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.GET)
	public Optional<Contact> getContact(@PathVariable Long id){
		return contactservice.findOne(id);
	}
	
	/**
	 * cette fonction prend un contact comme argument et puis elle le stock dans la base de donnée.
	 * @param contact
	 * @return
	 */
	@RequestMapping(value="/contacts",method=RequestMethod.POST)
	public Contact saveContact(@RequestBody Contact contact){
		return contactservice.save(contact);
	}
	
	/**
	 * cette fonction supprime le contact qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.DELETE)
	public Contact deleteContact(@PathVariable Long id){
		return contactservice.delete(id);
	}
	
	/**
	 * cette fonction fait la mise à jour d'un contact
	 * @param id
	 * @param contact
	 * @return
	 */
	@RequestMapping(value="/contacts/{id}",method=RequestMethod.PUT)
	public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact){
		return contactservice.update(id, contact);
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs contacts selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value="/chercherContact",method=RequestMethod.GET)
	public Page<Contact> searchContact( 
			@RequestParam(name="mc",defaultValue = "")String mc,
			@RequestParam(name="page",defaultValue = "0")int page,
			@RequestParam(name="size",defaultValue = "5")int size,
			@RequestParam(name="column")String column
			){
		return contactservice.search(mc, page, size, column);
	}
	

}
