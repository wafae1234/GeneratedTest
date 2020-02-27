package ma.dxc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dxc.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByUserName(String userName);
}
