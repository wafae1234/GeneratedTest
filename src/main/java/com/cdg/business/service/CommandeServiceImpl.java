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

import com.cdg.business.model.Commande;
import com.cdg.business.repository.CommandeRepository;
import com.cdg.business.repository.specs.CommandeSpecification;
import com.cdg.business.repository.specs.SearchCriteria;
import com.cdg.business.repository.specs.SearchOperation;

/**
 * Cette classe implémente l'interface CommandeService, elle utilise un object de la class CommandeRepository afin de profiter
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
public class CommandeServiceImpl implements CommandeService {
	
	/**
	 * On instancie un object de CommandeRepository avec l'annotation Autowired pour faire l'injection des dépendances.
	 */
	@Autowired
	private CommandeRepository commanderepository ;
	
	
	/**
	 * Cette fonction retourne tout les commandes.
	 */
	@Override
	public List<Commande> findAll() {
		 return commanderepository.findAll();
	}

	/**
	 * Cette fonction saisie un commande dans la base de données.
	 */
	@Override
	public Commande save(@Valid Commande commande) {
		return commanderepository.save(commande) ;
	}
	
	
	 /**
	  * Cette fonction fait la recherche sur un commande par mot clé et critére (column).
	  */
	@Override
	public Page<Commande> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
		commanderepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		CommandeSpecification commandeSpecification = new CommandeSpecification();
		System.out.println("column : "+column);
		System.out.println("mc : "+mc);
		if(isNumeric(mc)) {
			commandeSpecification.add(new SearchCriteria(column, mc, SearchOperation.EQUAL));
		}else {
			commandeSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
		}
		//pagination des resultats
		Page<Commande> msTitleList = commanderepository.findAll(commandeSpecification, pageable);
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
	  * Cette fonction fait la recherche sur un commande par deux mots clés et un critére (column).
	 * @throws ParseException 
	  */
	@Override
	public Page<Commande> searchTwoKeywords(String mc1, String mc2, int page, int size, String column){
		//recevoire toute la liste
		commanderepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		CommandeSpecification commandeSpecificationG = new CommandeSpecification();
		CommandeSpecification commandeSpecificationL = new CommandeSpecification();
		commandeSpecificationG.add(new SearchCriteria(column, mc1 , SearchOperation.GREATER_THAN_EQUAL));
		commandeSpecificationL.add(new SearchCriteria(column, mc2 , SearchOperation.LESS_THAN_EQUAL));
		Page<Commande> msTitleList = commanderepository.findAll(commandeSpecificationG.and(commandeSpecificationL),pageable);
       return msTitleList;
			
	}
	/**
	 * Cette fonction retourne un commande en fonction de l'id.
	 */
	@Override
	public Commande findOne(long id) {
		return commanderepository.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	/**
	 * cette fonction fait la mise à jour en fonction de l'id.
	 */
	@Override
	public Commande update(@Valid Long id, Commande commande) {
		commande.setId(id);
		return commanderepository.save(commanderepository.findById(id)
                .orElseThrow(EntityNotFoundException::new)
                .updateProperties(commande));
	}

	@Override
	public Commande delete(@Valid Long id) {
		Commande commande = commanderepository.findById(id).get();
		System.out.println(commande.toString());
		commande.setDeleted(true);
		return commanderepository.save(commande);
	}

	@Override
	public Page<Commande> findAllPageable(int page,int size) {
		Pageable pageable = PageRequest.of(page, size);
		return commanderepository.findAll(pageable);
	}

}
