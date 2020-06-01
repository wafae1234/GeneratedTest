package com.cdg.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cdg.business.model.Audit;

public interface AuditRepository extends JpaRepository<Audit , Long>,JpaSpecificationExecutor<Audit>{

}
