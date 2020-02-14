package ma.dxc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.dxc.model.Contact;

/**
 * Cet interface hérite de JpaRepository qui contient des fonctions prédéfinies qui sert à effectuer des actions sur la base
 * de données telles que (save,findOne,findAll...)
 * @author dchaa
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	/**
	 * JpaRepository ne contient pas une fonction qui cherche un objet par mot clé, donc on est obligé de la créer
	 * en spécifiant la requette, on va l'utilsier par la suite dans la classe ContactServiceImpl.
	 * @param mc
	 * @param pageable
	 * @return
	 */
	@Query("select c from Contact c where c.nom like :x")
	public Page<Contact> chercher(@Param("x")String mc, Pageable pageable);	
}
