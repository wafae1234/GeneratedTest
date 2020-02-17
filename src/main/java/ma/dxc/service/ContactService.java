package ma.dxc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import ma.dxc.model.Contact;

/**
 * Cet interface contient les déclaration des fonctions qui vont réagir avec la base données à l'aide de la couche DAO.
 * @author dchaa
 *
 */
public interface ContactService {
	public List<Contact> findAll();
	public Optional<Contact> findOne(long id);
	public Contact save(Contact contact);
	public Contact delete(Long id);
	public Page<Contact> search(String mc, int page, int size);
	public Contact update(Long id,Contact c);
}
