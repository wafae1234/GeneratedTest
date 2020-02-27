package ma.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dxc.model.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>{
	public AppRole findByRoleName(String roleName);
}

