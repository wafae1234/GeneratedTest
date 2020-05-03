package ma.dxc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ma.dxc.model.AppUser;

public interface UserService {
	public List<AppUser> findAll();
	public AppUser findOne(long id);
	public AppUser save(AppUser appUser);
	public Page<AppUser> search(String mc, int page, int size,String column);
	public AppUser update(Long id,AppUser c);
}
