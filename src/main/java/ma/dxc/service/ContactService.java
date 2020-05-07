package ma.dxc.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import ma.dxc.model.Contact;

/**
 * Cet interface contient les déclaration des fonctions qui vont réagir avec la base données à l'aide de la couche DAO.
 * @author dchaa
 *
 */
public interface ContactService {
	public List<Contact> findAll();
	public Page<Contact> findAllPageable(int page,int size);
	public Contact findOne(long id);
	public Contact save(Contact contact);
	public Contact delete(Long id);
	public Page<Contact> search(String mc, int page, int size,String column);
	public Page<Contact> searchTwoKeywords(String mc1,String mc2, int page, int size,String column) throws ParseException;
	public Contact update(Long id,Contact c);
}
