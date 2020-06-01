package com.cdg.business.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.cdg.business.model.Client;

/**
 * Cet interface contient les déclaration des fonctions qui vont réagir avec la base données à l'aide de la couche DAO.
 * @author dchaa
 *
 */
public interface ClientService {
	public List<Client> findAll();
	public Page<Client> findAllPageable(int page,int size);
	public Client findOne(long id);
	public Client save(Client client);
	public Client delete(Long id);
	public Page<Client> search(String mc, int page, int size,String column);
	public Page<Client> searchTwoKeywords(String mc1,String mc2, int page, int size,String column) throws ParseException;
	public Client update(Long id,Client c);
}
