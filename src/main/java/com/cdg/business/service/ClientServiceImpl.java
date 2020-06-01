package com.cdg.business.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Root;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cdg.business.model.Client;
import com.cdg.business.repository.ClientRepository;
import com.cdg.business.repository.specs.ClientSpecification;
import com.cdg.business.repository.specs.SearchCriteria;
import com.cdg.business.repository.specs.SearchOperation;

/**
 * Cette classe implémente l'interface ClientService, elle utilise un object de la class ClientRepository afin de profiter
 * des fonction fournis par JpaRepository.
 * @author dchaa
 *
 */
/**
 * @author MB
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	
	/**
	 * On instancie un object de ClientRepository avec l'annotation Autowired pour faire l'injection des dépendances.
	 */
	@Autowired
	private ClientRepository clientrepository ;
	
	
	/**
	 * Cette fonction retourne tout les clients.
	 */
	@Override
	public List<Client> findAll() {
		 return clientrepository.findAll();
	}

	/**
	 * Cette fonction saisie un client dans la base de données.
	 */
	@Override
	public Client save(@Valid Client client) {
		return clientrepository.save(client) ;
	}
	
	
	 /**
	  * Cette fonction fait la recherche sur un client par mot clé et critére (column).
	  */
	@Override
	public Page<Client> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
		clientrepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		ClientSpecification clientSpecification = new ClientSpecification();
		System.out.println("column : "+column);
		System.out.println("mc : "+mc);
		if(isNumeric(mc)) {
			clientSpecification.add(new SearchCriteria(column, mc, SearchOperation.EQUAL));
		}else {
			clientSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
		}
		//pagination des resultats
		Page<Client> msTitleList = clientrepository.findAll(clientSpecification, pageable);
        return msTitleList;
			
	}
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Long d = Long.parseLong(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	/**
	  * Cette fonction fait la recherche sur un client par deux mots clés et un critére (column).
	 * @throws ParseException 
	  */
	@Override
	public Page<Client> searchTwoKeywords(String mc1, String mc2, int page, int size, String column){
		//recevoire toute la liste
		clientrepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		ClientSpecification clientSpecificationG = new ClientSpecification();
		ClientSpecification clientSpecificationL = new ClientSpecification();
		clientSpecificationG.add(new SearchCriteria(column, mc1 , SearchOperation.GREATER_THAN_EQUAL));
		clientSpecificationL.add(new SearchCriteria(column, mc2 , SearchOperation.LESS_THAN_EQUAL));
		Page<Client> msTitleList = clientrepository.findAll(clientSpecificationG.and(clientSpecificationL),pageable);
       return msTitleList;
			
	}
	/**
	 * Cette fonction retourne un client en fonction de l'id.
	 */
	@Override
	public Client findOne(long id) {
		return clientrepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * cette fonction fait la mise à jour en fonction de l'id.
	 */
	@Override
	public Client update(@Valid Long id, Client client) {
		client.setId(id);
		return clientrepository.save(clientrepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                .updateProperties(client));
	}

	@Override
	public Client delete(@Valid Long id) {
		Client client = clientrepository.findById(id).get();
		System.out.println(client.toString());
		client.setDeleted(true);
		return clientrepository.save(client);
	}

	@Override
	public Page<Client> findAllPageable(int page,int size) {
		Pageable pageable = PageRequest.of(page, size);
		return clientrepository.findAll(pageable);
	}

}
