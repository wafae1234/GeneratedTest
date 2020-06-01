package com.cdg.business.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;

import com.cdg.business.dto.CommandeDTO;
import com.cdg.business.model.Commande;

public class CommandeMapperImpl implements CommandeMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public CommandeDTO toCommandeDTO(Commande commande) {
		return modelMapper.map(commande, CommandeDTO.class);
	}

	@Override
	public List<CommandeDTO> toCommandeDTOs(List<Commande> commandes) {
		
		Type listType = new TypeToken<List<CommandeDTO>>(){}.getType();
		List<CommandeDTO> commandeDTOs = modelMapper.map(commandes,listType);
		
		return commandeDTOs;
	}

	@Override
	public Commande toCommande(CommandeDTO commandeDTO) {
		return modelMapper.map(commandeDTO, Commande.class);
	}

	@Override
	public Page<CommandeDTO> toCommandeDTOsPageable(Page<Commande> commandes) {
		
		Type listType = new TypeToken<Page<CommandeDTO>>(){}.getType();
		Page<CommandeDTO> commandeDTOsPageable = modelMapper.map(commandes,listType);
		return commandeDTOsPageable;
	}

}
