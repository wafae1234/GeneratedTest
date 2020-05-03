package ma.dxc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.dxc.model.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long>
		,JpaSpecificationExecutor<AppRole>{
	@Query("select c from AppRole c where c.roleName like :x ")
	public Page<AppRole> chercher(@Param("x")String mc, Pageable pageable);
	public AppRole findByRoleName(String roleName);
}

