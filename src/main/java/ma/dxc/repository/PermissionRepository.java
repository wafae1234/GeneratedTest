package ma.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dxc.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
