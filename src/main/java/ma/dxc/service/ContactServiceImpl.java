package ma.dxc.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

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
	  * Cette fonction fait la recherche sur un contact par mot clé et critére (column).
	  */
	@Override
	public Page<Contact> search(String mc, int page, int size, String column) {
		List<Contact> list =null;
		if(mc.isEmpty() || column.isEmpty()) {
			list= contactrepository.findAll();
		}else
		
		 list = contactrepository.findAll(new Specification<Contact>() {
			
			@Override
			public Predicate toPredicate(Root<Contact> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				
				Predicate p = cb.conjunction();
				p = cb.and(p, cb.like(root.get(column), "%"+mc+"%"));
				return p;
			}
		});
		 //Cast List<Contact> to Page<Contact>
		 PageImpl<Contact> pageImpl = new PageImpl<Contact>(list,  PageRequest.of(page, size, Sort.unsorted()), list.size());
		 return pageImpl;
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

	@Override
	public Contact delete(@Valid Long id) {
		Contact contact = contactrepository.findById(id).get();
		System.out.println(contact.toString());
		contact.setDeleted(true);
		return contactrepository.save(contact);
	}

}
