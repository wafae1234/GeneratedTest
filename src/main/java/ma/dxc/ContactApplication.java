package ma.dxc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.dxc.model.Contact;
import ma.dxc.repository.ContactRepository;
import ma.dxc.service.ContactServiceImpl;

@SpringBootApplication
public class ContactApplication implements CommandLineRunner {
	@Autowired
	private ContactServiceImpl contactservice;

	public static void main(String[] args) {
		SpringApplication.run(ContactApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DateFormat df= new SimpleDateFormat("dd/MM/yyyy");
		/*
		contactservice.save(new Contact("Hassani","Mohamed",df.parse("12/01/1998"),"hassan@gmail.com","0766873000","hassan.jpg"));
		contactservice.save(new Contact("di","diyaaEddine",df.parse("12/01/1998"),"diae@gmail.com","07668730000","diae.jpg"));
		contactservice.save(new Contact("Chaali","Ithar",df.parse("12/01/2010"),"ithar@gmail.com","0766873000","ithar.jpg"));
		*/
		
		contactservice.findAll().forEach(c -> {
			System.out.println(c.getNom());
			
		});
		
		System.out.println(contactservice.findOne(1));
	}

}
