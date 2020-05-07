package ma.dxc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ma.dxc.model.Audit;

public interface AuditRepository extends JpaRepository<Audit , Long>,JpaSpecificationExecutor<Audit>{

}
