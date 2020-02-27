package ma.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dxc.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	public AppUser findByUsername(String username);
}
