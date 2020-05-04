package ma.dxc.service.audit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ma.dxc.model.Audit;
import ma.dxc.repository.AuditRepository;

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
		return null;
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
