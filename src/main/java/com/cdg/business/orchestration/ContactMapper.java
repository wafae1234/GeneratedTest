package com.cdg.business.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.cdg.business.dto.ContactDTO;
import com.cdg.business.model.Contact;

//@Mapper
public interface ContactMapper {
	
	public ContactDTO toContactDTO(Contact contact);
	
	public List<ContactDTO> toContactDTOs(List<Contact> contacts);
	
	public Contact toContact(ContactDTO contactDTO);
	
	public Page<ContactDTO> toContactDTOsPageable(Page<Contact> contacts);
	
	ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
	
	
}
