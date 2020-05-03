package ma.dxc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.dxc.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> 
		,JpaSpecificationExecutor<AppUser>{
	@Query("select c from AppUser c where c.username like :x ")
	public Page<AppUser> chercher(@Param("x")String mc, Pageable pageable);
	public AppUser findByUsername(String username);
}
