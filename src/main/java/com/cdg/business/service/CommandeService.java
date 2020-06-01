package com.cdg.business.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.cdg.business.model.Commande;

/**
 * Cet interface contient les déclaration des fonctions qui vont réagir avec la base données à l'aide de la couche DAO.
 * @author dchaa
 *
 */
public interface CommandeService {
	public List<Commande> findAll();
	public Page<Commande> findAllPageable(int page,int size);
	public Commande findOne(long id);
	public Commande save(Commande commande);
	public Commande delete(Long id);
	public Page<Commande> search(String mc, int page, int size,String column);
	public Page<Commande> searchTwoKeywords(String mc1,String mc2, int page, int size,String column) throws ParseException;
	public Commande update(Long id,Commande c);
}
