package ma.dxc.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.Contact;
import ma.dxc.repository.ContactRepository;
import ma.dxc.repository.specs.ContactSpecification;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;

/**
 * Cette classe implémente l'interface ContactService, elle utilise un object de la class ContactRepository afin de profiter
 * des fonction fournis par JpaRepository.
 * @author dchaa
 *
 */
/**
 * @author MB
 *
 */
@Service
public class ContactServiceImpl implements ContactService {
	
	/**
	 * On instancie un object de ContactRepository avec l'annotation Autowired pour faire l'injection des dépendances.
	 */
	@Autowired
	private ContactRepository contactrepository ;
	
	
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
	  * Cette fonction fait la recherche sur un contact par mot clé et critére (column).
	  */
	@Override
	public Page<Contact> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
		contactrepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		ContactSpecification contactSpecification = new ContactSpecification();
		//le cas où le mot clé ou le nom de la colomne est vide
		if(mc.isEmpty() || column.isEmpty()) 
            contactSpecification.add(new SearchCriteria("email", "@", SearchOperation.MATCH));
		//si non
		else
            contactSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
		//pagination des resultats
		Page<Contact> msTitleList = contactrepository.findAll(contactSpecification, pageable);
        return msTitleList;
			
	}
	/**
	 * Cette fonction retourne un contact en fonction de l'id.
	 */
	@Override
	public Contact findOne(long id) {
		return contactrepository.getOne(id);
	}

	/**
	 * cette fonction fait la mise à jour en fonction de l'id.
	 */
	@Override
	public Contact update(@Valid Long id, Contact contact) {
		contact.setId(id);
		return contactrepository.save(contact);
	}

	@Override
	public Contact delete(@Valid Long id) {
		Contact contact = contactrepository.findById(id).get();
		System.out.println(contact.toString());
		contact.setDeleted(true);
		return contactrepository.save(contact);
	}

}
