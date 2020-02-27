package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.ContactDTO;
import ma.dxc.model.Contact;

@Mapper
public interface ContactMapper {
	
	public ContactDTO toContactDTO(Contact contact);
	
	public List<ContactDTO> toContactDTOs(List<Contact> contacts);
	
	public Contact toContact(ContactDTO contactDTO);
	
	ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
	
	
}
