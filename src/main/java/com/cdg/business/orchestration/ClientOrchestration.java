package com.cdg.business.orchestration;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cdg.business.dto.ClientDTO;
import com.cdg.business.model.Client;
import com.cdg.business.service.ClientServiceImpl;

@Service
public class ClientOrchestration {
	
	/**
	 * On déclare un objet de la classe ClientServiceImpl qui va lui meme faire appel à la couche DAO
	 * afin d'interagir avec la base de données.
	 */
	@Autowired
	private ClientServiceImpl clientservice;
	
	/**
	 * cette fonction nous retourne la liste des clients.
	 * @return
	 */
	public List<ClientDTO> getClients(){
		return ClientMapper.INSTANCE.toClientDTOs(clientservice.findAll());
	}
	
	/**
	 * cette fonction nous retourne le client qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	public ClientDTO getClient(Long id){
		return ClientMapper.INSTANCE.toClientDTO(clientservice.findOne(id));
	}
	
	/**
	 * cette fonction prend un client comme argument et puis elle le stock dans la base de donnée.
	 * @param client
	 * @return
	 */
	public ClientDTO saveClient(ClientDTO clientDTO){
		Client client = ClientMapper.INSTANCE.toClient(clientDTO);
		client = clientservice.save(client);
		return ClientMapper.INSTANCE.toClientDTO(client);
	}
	
	/**
	 * cette fonction supprime le client qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	public Boolean deleteClient(Long id){
		clientservice.delete(id);
		return true;
	}
	
	/**
	 * cette fonction fait la mise à jour d'un client
	 * @param id
	 * @param client
	 * @return
	 */
	public ClientDTO updateClient(Long id, ClientDTO clientDTO){
		Client client = ClientMapper.INSTANCE.toClient(clientDTO);
		clientDTO =  ClientMapper.INSTANCE.toClientDTO(clientservice.update(id, client));
		return clientDTO;
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs clients selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ClientDTO> searchClient( String mc,int page,int size,String column){
		Page<Client> clients = clientservice.search(mc, page, size, column);
		List<ClientDTO> clientDTOs = ClientMapper.INSTANCE.toClientDTOs(clients.getContent());
		return new PageImpl<>(clientDTOs,PageRequest.of(page, size),clients.getTotalElements());
	}
	
	public Page<ClientDTO> searchTwoKeywords( String mc1,String mc2,int page,int size,String column) throws ParseException{
		Page<Client> clients = clientservice.searchTwoKeywords(mc1, mc2, page, size, column);
		List<ClientDTO> clientDTOs = ClientMapper.INSTANCE.toClientDTOs(clients.getContent());
		return new PageImpl<>(clientDTOs,PageRequest.of(page, size),clients.getTotalElements());
	}
	
	
	public Page<ClientDTO> getPageofClients(int page,int size){
		Page<Client> clients = clientservice.findAllPageable(page, size);
		Page<ClientDTO> clientDTOs = ClientMapper.INSTANCE.toClientDTOsPageable(clients);
		return clientDTOs;
	}
	
	

}
