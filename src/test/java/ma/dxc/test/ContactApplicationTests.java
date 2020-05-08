package ma.dxc.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ma.dxc.dto.ContactDTO;
import ma.dxc.model.Contact;
import ma.dxc.orchestration.ContactMapper;
import ma.dxc.service.ContactServiceImpl;

@SpringBootTest
class ContactApplicationTests {
	
	@Autowired
	private ContactServiceImpl contactservice;

	@Test
	void contextLoads() {
	}
	
	@Test
	void contactToContactDTO() throws ParseException {
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Contact contact = new Contact( "BENALI",  "mohamed", sdf.parse("09/04/1996"), "mohamed@gmail.com", "0605040102","mohamed.jpeg");
		Contact contact = contactservice.findAll().get(0);
		
		ContactDTO contactDTO = ContactMapper.INSTANCE.toContactDTO(contact);
		
		assertThat(contactDTO).isNotNull();
		assertThat(contactDTO.getNom()).isEqualTo(contact.getNom());
		assertThat(contactDTO.getPrenom()).isEqualTo("Mohamed");
		contactDTO.toString();
	}
	
	@Test
	void contactsToContactDTOs() throws ParseException {
		List<Contact> contacts = contactservice.findAll();
		
		List<ContactDTO> contactsDTO = ContactMapper.INSTANCE.toContactDTOs(contacts);
		
		assertThat(contactsDTO).isNotEmpty();
		assertThat(contactsDTO).isNotNull();
		assertThat(contactsDTO.get(0)).isNotNull();
		assertThat(contactsDTO.get(0).getNom()).isEqualTo("Hassani");
		
		
	}
	
	@Test
	void isNumeric() throws ParseException {
		assertThat(contactservice.isNumeric("22")).isTrue();
		assertThat(contactservice.isNumeric("5.05")).isFalse();
		assertThat(contactservice.isNumeric("-200")).isTrue(); 
		assertThat(contactservice.isNumeric("10.0d")).isFalse();
		assertThat(contactservice.isNumeric("   22   ")).isFalse();
		  
		assertThat(contactservice.isNumeric(null)).isFalse();
		assertThat(contactservice.isNumeric("")).isFalse();
		assertThat(contactservice.isNumeric("abc")).isFalse();
		
		
	}

}
