package ma.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dxc.model.Audit;

public interface AuditRepository extends JpaRepository<Audit , Long>{

}
