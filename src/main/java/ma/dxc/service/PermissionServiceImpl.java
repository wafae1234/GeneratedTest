package ma.dxc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.Contact;
import ma.dxc.model.Permission;
import ma.dxc.repository.PermissionRepository;
import ma.dxc.repository.specs.ContactSpecification;
import ma.dxc.repository.specs.PermissionSpecification;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionRepository permissionrepository ;

	@Override
	public List<Permission> findAll() {
		// TODO Auto-generated method stub
		return permissionrepository.findAll();
	}

	@Override
	public Permission findOne(long id) {
		// TODO Auto-generated method stub
		return permissionrepository.getOne(id);
	}

	@Override
	public Permission save(Permission permission) {
		// TODO Auto-generated method stub
		return permissionrepository.save(permission);
	}

	@Override
	public Page<Permission> search(String mc, int page, int size, String column) {
		//recevoire toute la liste
				permissionrepository.findAll();
				Pageable pageable = PageRequest.of(page, size);
				PermissionSpecification permissionSpecification = new PermissionSpecification();
				//le cas où le mot clé ou le nom de la colomne est vide
				if(mc.isEmpty() || column.isEmpty()) 
		            permissionSpecification.add(new SearchCriteria("id", "0", SearchOperation.IS_NOT_EMPTY));
				//si non
				else
		            permissionSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
				//pagination des resultats
				Page<Permission> msTitleList = permissionrepository.findAll(permissionSpecification, pageable);
		        return msTitleList;
	}

	@Override
	public Permission update(Long id, Permission permission) {
		// TODO Auto-generated method stub
		permission.setId(id);
		return permissionrepository.saveAndFlush(permission);
	}

}
