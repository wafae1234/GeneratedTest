package ma.dxc.service.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.Audit;
import ma.dxc.repository.AuditRepository;
import ma.dxc.repository.specs.AuditSpecification;
import ma.dxc.repository.specs.SearchCriteria;
import ma.dxc.repository.specs.SearchOperation;

@Service
public class AuditServiceImpl implements AuditService {
	
	@Autowired
	AuditRepository auditRepository;
	
	@Override
	public List<Audit> findAll() {
		// TODO Auto-generated method stub
		return auditRepository.findAll();
	}

	@Override
	public Audit findOne(long id) {
		// TODO Auto-generated method stub
		return auditRepository.getOne(id);
	}

	@Override
	public Audit save(Audit audit) {
		// TODO Auto-generated method stub
		return auditRepository.save(audit);
	}

	@Override
	public Page<Audit> search(String mc, int page, int size, String column) {
		// TODO Auto-generated method stub
		auditRepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		AuditSpecification auditSpecification = new AuditSpecification();
		if(isNumeric(mc)) {
			auditSpecification.add(new SearchCriteria(column, mc, SearchOperation.EQUAL));
		}else {
			auditSpecification.add(new SearchCriteria(column, mc, SearchOperation.MATCH));
		}
		Page<Audit> msTitleList = auditRepository.findAll(auditSpecification,pageable);
		return msTitleList;
	}
	
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        Long d = Long.parseLong(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	@Override
	public Page<Audit> searchTwoKeywords(String mc1, String mc2, int page, int size, String column) {
		// TODO Auto-generated method stub
		auditRepository.findAll();
		Pageable pageable = PageRequest.of(page, size);
		AuditSpecification auditSpecificationG = new AuditSpecification();
		AuditSpecification auditSpecificationL = new AuditSpecification();
		auditSpecificationG.add(new SearchCriteria(column, mc1, SearchOperation.GREATER_THAN_EQUAL));
		auditSpecificationL.add(new SearchCriteria(column, mc2, SearchOperation.LESS_THAN_EQUAL));
		Page<Audit> msTitleList = auditRepository.findAll(auditSpecificationL.and(auditSpecificationG),pageable);
		return msTitleList;
	}
	
	

	@Override
	public Audit update(Long id, Audit audit) {
		// TODO Auto-generated method stub
		return auditRepository.saveAndFlush(audit);
	}

	@Override
	public Page<Audit> findAllPageable(int page, int size) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size);
		return auditRepository.findAll(pageable);
	}

}
