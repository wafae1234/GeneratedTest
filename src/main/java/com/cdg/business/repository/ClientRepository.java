package com.cdg.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdg.business.model.Client;

/**
 * Cet interface hérite de JpaRepository qui contient des fonctions prédéfinies qui sert à effectuer des actions sur la base
 * de données telles que (save,findOne,findAll...)
 * @author dchaa
 *
 */
public interface ClientRepository extends JpaRepository<Client, Long>,JpaSpecificationExecutor<Client> {
	
	/**
	 * JpaRepository ne contient pas une fonction qui cherche un objet par mot clé, donc on est obligé de la créer
	 * en spécifiant la requette, on va l'utilsier par la suite dans la classe ClientServiceImpl.
	 * @param mc
	 * @param pageable
	 * @return
	 */
	@Query("select c from Client c where c.nom like :x ")
	public Page<Client> chercher(@Param("x")String mc, Pageable pageable);	
}
