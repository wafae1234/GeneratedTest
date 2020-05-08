package ma.dxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.AppRole;
import ma.dxc.model.Contact;
import ma.dxc.repository.RoleRepository;
import ma.dxc.repository.specs.ContactSpecification;
import ma.dxc.repository.specs.RoleSpecification;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<AppRole> findAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public AppRole findOne(long id) {
		// TODO Auto-generated method stub
		return roleRepository.getOne(id);
	}

	@Override
	public AppRole save(AppRole appRole) {
		// TODO Auto-generated method stub
		return roleRepository.save(appRole);
	}

	@Override
	public Page<AppRole> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
				roleRepository.findAll();
				Pageable pageable = PageRequest.of(page, size);
				RoleSpecification roleSpecification = new RoleSpecification();
				if(isNumeric(mc)) {
					roleSpecification.add(new SearchCriteria(column, mc, SearchOperation.EQUAL));
				}else {
					roleSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
				}
				//pagination des resultats
				Page<AppRole> msTitleList = roleRepository.findAll(roleSpecification, pageable);
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

	@Override
	public AppRole update(Long id, AppRole appRole) {
		// TODO Auto-generated method stub
		appRole.setId(id);
		return roleRepository.saveAndFlush(appRole);
	}

	@Override
	public Page<AppRole> findAllPageable(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return roleRepository.findAll(pageable);
	}

}
