package com.cdg.business.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.cdg.business.dto.CommandeDTO;
import com.cdg.business.model.Commande;

//@Mapper
public interface CommandeMapper {
	
	public CommandeDTO toCommandeDTO(Commande commande);
	
	public List<CommandeDTO> toCommandeDTOs(List<Commande> commandes);
	
	public Commande toCommande(CommandeDTO commandeDTO);
	
	public Page<CommandeDTO> toCommandeDTOsPageable(Page<Commande> commandes);
	
	CommandeMapper INSTANCE = Mappers.getMapper( CommandeMapper.class );
	
	
}
