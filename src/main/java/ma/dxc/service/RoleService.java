package ma.dxc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ma.dxc.model.AppRole;

public interface RoleService {
	
	public List<AppRole> findAll();
	public AppRole findOne(long id);
	public Page<AppRole> findAllPageable(int page,int size);
	public AppRole save(AppRole appRole);
	public Page<AppRole> search(String mc, int page, int size,String column);
	public AppRole update(Long id,AppRole c);
}
