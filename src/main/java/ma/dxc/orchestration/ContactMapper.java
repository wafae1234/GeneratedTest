package ma.dxc.orchestration;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import ma.dxc.dto.ContactDTO;
import ma.dxc.model.Contact;

@Mapper
public interface ContactMapper {
	/**
	 * cette fonction converte la classe entite à une classe dto
	 * 
	 * @param contact
	 * @return
	 */
	public ContactDTO toContactDTO(Contact contact);
	/**
	 * 
	 * @param contacts
	 * @return
	 */
	public List<ContactDTO> toContactDTOs(List<Contact> contacts);
	/**
	 * 
	 * @param contactDTO
	 * @return
	 */
	public Contact toContact(ContactDTO contactDTO);
	/**
	 * 
	 */
	ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );
	
	
}
