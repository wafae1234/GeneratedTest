package com.cdg.business.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.cdg.business.dto.ClientDTO;
import com.cdg.business.model.Client;

//@Mapper
public interface ClientMapper {
	
	public ClientDTO toClientDTO(Client client);
	
	public List<ClientDTO> toClientDTOs(List<Client> clients);
	
	public Client toClient(ClientDTO clientDTO);
	
	public Page<ClientDTO> toClientDTOsPageable(Page<Client> clients);
	
	ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );
	
	
}
