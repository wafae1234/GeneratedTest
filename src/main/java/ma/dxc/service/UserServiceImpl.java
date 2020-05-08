package ma.dxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.AppUser;
import ma.dxc.model.Permission;
import ma.dxc.repository.UserRepository;
import ma.dxc.repository.specs.PermissionSpecification;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;
import ma.dxc.repository.specs.UserSpecification;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<AppUser> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public AppUser findOne(long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public AppUser save(AppUser appUser) {
		// TODO Auto-generated method stub
		return userRepository.save(appUser);
	}

	@Override
	public Page<AppUser> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
		userRepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		UserSpecification userSpecification = new UserSpecification();
		if(isNumeric(mc)) {
			userSpecification.add(new SearchCriteria(column, mc, SearchOperation.EQUAL));
		}else {
			userSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
		}
		//pagination des resultats
		Page<AppUser> msTitleList = userRepository.findAll(userSpecification,pageable);
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
	public AppUser update(Long id, AppUser appUser) {
		// TODO Auto-generated method stub
		appUser.setId(id);
		return userRepository.saveAndFlush(appUser);
	}

	@Override
	public Page<AppUser> findAllPageable(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return userRepository.findAll(pageable);
	}

}
