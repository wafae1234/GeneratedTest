package ma.dxc.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.dxc.model.Contact;
import ma.dxc.repository.ContactRepository;

/**
 * Cette classe implémente l'interface ContactService, elle utilise un object de la class ContactRepository afin de profiter
 * des fonction fournis par JpaRepository.
 * @author dchaa
 *
 */
@Service
public class ContactServiceImpl implements ContactService {
	
	/**
	 * On instancie un object de ContactRepository avec l'annotation Autowired pour faire l'injection des dépendances.
	 */
	@Autowired
	private ContactRepository contactrepository;
	
	/**
	 * Cette fonction retourne tout les contacts.
	 */
	@Override
	public List<Contact> findAll() {
		 return contactrepository.findAll();
	}

	/**
	 * Cette fonction saisie un contact dans la base de données.
	 */
	@Override
	public Contact save(@Valid Contact contact) {
		return contactrepository.save(contact) ;
	}
	
	/**
	 * Cette fonction supprime un contact.
	 */
	@Override
	public boolean delete(Long id) {
		contactrepository.deleteById(id);
		return true;
	}

	 /**
	  * Cette fonction fait la recherche sur un contact par mot clé.
	  */
	@Override
	public Page<Contact> search(String mc, int page, int size) {
		return contactrepository.chercher("%"+ mc +"%", PageRequest.of(page, size));
	}

	/**
	 * Cette fonction retourne un contact en fonction de l'id.
	 */
	@Override
	public Optional<Contact> findOne(long id) {
		return contactrepository.findById(id);
	}

	/**
	 * cette fonction fait la mise à jour en fonction de l'id.
	 */
	@Override
	public Contact update(@Valid Long id, Contact contact) {
		contact.setId(id);
		return contactrepository.save(contact);
	}

}
