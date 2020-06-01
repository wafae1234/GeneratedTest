package com.cdg.business.orchestration;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cdg.business.dto.CommandeDTO;
import com.cdg.business.model.Commande;
import com.cdg.business.service.CommandeServiceImpl;

@Service
public class CommandeOrchestration {
	
	/**
	 * On déclare un objet de la classe CommandeServiceImpl qui va lui meme faire appel à la couche DAO
	 * afin d'interagir avec la base de données.
	 */
	@Autowired
	private CommandeServiceImpl commandeservice;
	
	/**
	 * cette fonction nous retourne la liste des commandes.
	 * @return
	 */
	public List<CommandeDTO> getCommandes(){
		return CommandeMapper.INSTANCE.toCommandeDTOs(commandeservice.findAll());
	}
	
	/**
	 * cette fonction nous retourne le commande qui correspond à l'ID de l'entrée
	 * @param id
	 * @return
	 */
	public CommandeDTO getCommande(Long id){
		return CommandeMapper.INSTANCE.toCommandeDTO(commandeservice.findOne(id));
	}
	
	/**
	 * cette fonction prend un commande comme argument et puis elle le stock dans la base de donnée.
	 * @param commande
	 * @return
	 */
	public CommandeDTO saveCommande(CommandeDTO commandeDTO){
		Commande commande = CommandeMapper.INSTANCE.toCommande(commandeDTO);
		commande = commandeservice.save(commande);
		return CommandeMapper.INSTANCE.toCommandeDTO(commande);
	}
	
	/**
	 * cette fonction supprime le commande qui correspond à l'id de l'entrée
	 * @param id
	 * @return
	 */
	public Boolean deleteCommande(Long id){
		commandeservice.delete(id);
		return true;
	}
	
	/**
	 * cette fonction fait la mise à jour d'un commande
	 * @param id
	 * @param commande
	 * @return
	 */
	public CommandeDTO updateCommande(Long id, CommandeDTO commandeDTO){
		Commande commande = CommandeMapper.INSTANCE.toCommande(commandeDTO);
		commandeDTO =  CommandeMapper.INSTANCE.toCommandeDTO(commandeservice.update(id, commande));
		return commandeDTO;
	}
	
	/**
	 * cette fonction fait la recherche sur un ou plusieurs commandes selon un mot clé saisie, on precise aussi les
	 * paramètres size et page.
	 * @param mc
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<CommandeDTO> searchCommande( String mc,int page,int size,String column){
		Page<Commande> commandes = commandeservice.search(mc, page, size, column);
		List<CommandeDTO> commandeDTOs = CommandeMapper.INSTANCE.toCommandeDTOs(commandes.getContent());
		return new PageImpl<>(commandeDTOs,PageRequest.of(page, size),commandes.getTotalElements());
	}
	
	public Page<CommandeDTO> searchTwoKeywords( String mc1,String mc2,int page,int size,String column) throws ParseException{
		Page<Commande> commandes = commandeservice.searchTwoKeywords(mc1, mc2, page, size, column);
		List<CommandeDTO> commandeDTOs = CommandeMapper.INSTANCE.toCommandeDTOs(commandes.getContent());
		return new PageImpl<>(commandeDTOs,PageRequest.of(page, size),commandes.getTotalElements());
	}
	
	
	public Page<CommandeDTO> getPageofCommandes(int page,int size){
		Page<Commande> commandes = commandeservice.findAllPageable(page, size);
		Page<CommandeDTO> commandeDTOs = CommandeMapper.INSTANCE.toCommandeDTOsPageable(commandes);
		return commandeDTOs;
	}
	
	

}
