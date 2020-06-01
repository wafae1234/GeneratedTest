package com.cdg.business.orchestration;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;

import com.cdg.business.dto.ClientDTO;
import com.cdg.business.model.Client;

public class ClientMapperImpl implements ClientMapper {
	ModelMapper modelMapper = new ModelMapper();

	@Override
	public ClientDTO toClientDTO(Client client) {
		return modelMapper.map(client, ClientDTO.class);
	}

	@Override
	public List<ClientDTO> toClientDTOs(List<Client> clients) {
		
		Type listType = new TypeToken<List<ClientDTO>>(){}.getType();
		List<ClientDTO> clientDTOs = modelMapper.map(clients,listType);
		
		return clientDTOs;
	}

	@Override
	public Client toClient(ClientDTO clientDTO) {
		return modelMapper.map(clientDTO, Client.class);
	}

	@Override
	public Page<ClientDTO> toClientDTOsPageable(Page<Client> clients) {
		
		Type listType = new TypeToken<Page<ClientDTO>>(){}.getType();
		Page<ClientDTO> clientDTOsPageable = modelMapper.map(clients,listType);
		return clientDTOsPageable;
	}

}
