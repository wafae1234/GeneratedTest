package com.cdg.business.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.cdg.business.model.AppUser;

public interface UserService {
	public List<AppUser> findAll();
	public AppUser findOne(long id);
	public Page<AppUser> findAllPageable(int page,int size);
	public AppUser save(AppUser appUser);
	public Page<AppUser> search(String mc, int page, int size,String column);
	public AppUser update(Long id,AppUser c);
}
